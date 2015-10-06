package com.alphatica.genotick.genotick;

import java.io.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

enum DebugInfo {
    NONE,
    LINE,
    TRACE
}
class DebugParams {
    boolean classEnable;
    String prefix;
    DebugInfo debugInfo;
    OutputStream stream;
    String allowedPattern;
    boolean showTime;
    Set<String> allowedClasses;

    DebugParams() {
        prefix = null;
        debugInfo = DebugInfo.NONE;
        stream = System.out;
        allowedPattern = null;
        classEnable = true;
        showTime = false;
        allowedClasses = new HashSet<>();
    }

    DebugParams(DebugParams params) {
        prefix = params.prefix;
        debugInfo = params.debugInfo;
        stream = params.stream;
        allowedPattern = params.allowedPattern;
        classEnable = params.classEnable;
        showTime = params.showTime;
        allowedClasses = new HashSet<>(params.allowedClasses);
    }
}
public class Debug {
    private static final DebugParams params = new DebugParams();
    private static HashMap<Integer,Instance> debugInstances;

    public static Instance getInstance() {
        return new Instance(params);
    }

    public static void setOutputStream(OutputStream outputStream) {
        Debug.setOutPutStream(params, outputStream);
    }
    public static void setPrefix(String prefix) {
        Debug.setPrefix(params, prefix);
    }
    public static void d(Object... output) {
        Debug.log(params, output);
    }
    public static void setDebugInfoNone() {
        Debug.setDebugInfo(params,DebugInfo.NONE);
    }
    public static void setDebugInfoFileAndLine() {
        Debug.setDebugInfo(params,DebugInfo.LINE);
    }
    public static void setDebugInfoFullTrace() {
        Debug.setDebugInfo(params,DebugInfo.TRACE);
    }

    public static void setAllowedPattern(String regex) {
        Debug.setAllowedPattern(params,regex);
    }

    public static void setEnabled(boolean enabled) {
        Debug.setEnabled(params,enabled);
    }

    public static void setShowTime(boolean enabled) {
        Debug.setShowTime(params,enabled);
    }

    public static  void allowedClasses(Class... classes) {
        Debug.allowClasses(params, classes);
    }

    public static void allowAllClasses() {
        Debug.allowAllClasses(params);
    }

    private static void allowAllClasses(DebugParams params) {
        params.allowedClasses.clear();
    }

    public static void toFile(String path) {
        Debug.setOutputStream(new ToFile(path));
    }

    public static void addDebug(Instance instance, int id) {
        if(debugInstances == null)
            debugInstances = new HashMap<>();
        debugInstances.put(id,instance);
    }

    public static Instance getDebug(int i) {
        if(debugInstances == null)
            throw new RuntimeException("No debug instances");
        Instance instance = debugInstances.get(i);
        if(instance == null)
            throw new RuntimeException("No debug instance " + String.valueOf(i));
        return instance;
    }

    /******************* END OF PUBLIC METHODS ****************************************/

    private static void allowClasses(DebugParams par, Class[] classes) {
        par.allowedClasses.clear();
        for(Class aClass: classes)
            par.allowedClasses.add(aClass.getName());
    }
    private static void setShowTime(DebugParams par, boolean enabled) {
        par.showTime = enabled;
    }
    private static void setAllowedPattern(DebugParams par, String regex) {
        par.allowedPattern = regex;
    }
    private static boolean isStackElementDebugClassFile(StackTraceElement element) {
        StackTraceElement [] stackTraceElements = new Throwable().getStackTrace();
        if(stackTraceElements == null)
            return false;
        String name = stackTraceElements[0].getFileName();
        if(name == null)
            return false;
        if(element == null || element.getFileName() == null)
            return false;
        return element.getFileName().equals(name);
    }
    private static void setDebugInfo(DebugParams params, DebugInfo debugInfo) {
        params.debugInfo = debugInfo;
    }

    private static void setOutPutStream(DebugParams params, OutputStream outputStream) {
        params.stream = outputStream;
    }

    private static void print(OutputStream stream, Object object) {
        if(object == null) {
            printBytes(stream,"(null)".getBytes());
        } else if(object instanceof Iterable) {
            printIterable(stream, (Iterable) object);
        } else if(object instanceof Throwable) {
            printThrowable(stream, (Throwable) object);
        } else if(object.getClass().isArray()) {
            Object [] array = toObjectArray(object);
            printArray(stream,array);
        } else {
            printBytes(stream, object.toString().getBytes());
        }
    }

    private static Object[] toObjectArray(Object object) {
        int length = Array.getLength(object);
        Object[] resultArray;
        resultArray = new Object[length];
        for (int i = 0; i < length; i++) {
            resultArray[i] = Array.get(object, i);
        }
        return resultArray;
    }

    private static void printThrowable(OutputStream stream, Throwable throwable) {
        printBytes(stream,("Exception " + throwable.toString()).getBytes());
        printNewLine(stream);
        StackTraceElement [] stackTraceElements = throwable.getStackTrace();
        for(int i = stackTraceElements.length - 1; i >= 0; i--) {
            StackTraceElement element = stackTraceElements[i];
            printBytes(stream,element.getFileName().getBytes());
            printBytes(stream,":".getBytes());
            printBytes(stream,String.valueOf(element.getLineNumber()).getBytes());
            printNewLine(stream);
        }
    }

    private static void printBytes(OutputStream stream, byte[] bytes) {
        try {
            stream.write(bytes);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private static void printArray(OutputStream stream, Object [] array) {
        print(stream, "[");
        boolean firstItem = true;
        for (Object object : array) {
            if ( ! firstItem) {
                print(stream, " , ");
            }
            print(stream, object);
            firstItem = false;
        }
        print(stream, "]");

    }

    private static void printIterable(OutputStream stream, Iterable iterable) {
        print(stream, "[");
        boolean firstItem = true;
        for (Object object : iterable) {
            if ( ! firstItem) {
                print(stream, " , ");
            }
            print(stream, object);
            firstItem = false;
        }
        print(stream, "]");
    }

    private static void log(DebugParams par, Object... output) {
        if(!par.classEnable)
            return;
        if(output.length == 0)
            return;
        if(par.allowedPattern != null && !output[0].toString().matches(par.allowedPattern))
            return;
        if(!par.allowedClasses.isEmpty() && !isClassAllowed(par.allowedClasses))
            return;
        printTime(par);
        printSourceCodeInfo(par);
        printPrefix(par);
        printOutput(par, output);
        printNewLine(par.stream);
    }

    private static boolean isClassAllowed(Set<String> names) {
        StackTraceElement [] stackTraceElements = new Throwable().getStackTrace();
        for(int i = 0; i < stackTraceElements.length; i++) {
            if(isStackElementDebugClassFile(stackTraceElements[i]))
                continue;
            return names.contains(stackTraceElements[i].getClassName());
        }
        return false;
    }
    private static void printTime(DebugParams par) {
        if(par.showTime == false)
            return;
        DateFormat df = new SimpleDateFormat("Y/M/d HH:mm:ss:S",Locale.getDefault());
        Date date = new Date();
        String string = df.format(date);
        print(par.stream,string);
        print(par.stream," ");
    }
    private static void printSourceCodeInfo(DebugParams par) {
        switch (par.debugInfo) {
            case NONE: return;
            case LINE: printSourceCodeFileAndLine(par); return;
            case TRACE: printSourceCodeStackTrace(par);  return;
        }
    }

    private static void printSourceCodeStackTrace(DebugParams par) {
        StackTraceElement [] elements = new Throwable().getStackTrace();
        for(int i = elements.length - 1 ; i >= 0; i--) {
            StackTraceElement element = elements[i];
            if(isStackElementDebugClassFile(element))
                continue;
            printElementInfo(par.stream,element);
        }
    }

    private static void printSourceCodeFileAndLine(DebugParams par) {
        StackTraceElement [] elements = new Throwable().getStackTrace();
        for (StackTraceElement element : elements) {
            if (!isStackElementDebugClassFile(element)) {
                printElementInfo(par.stream, element);
                break;
            }
        }
    }

    private static void printElementInfo(OutputStream stream, StackTraceElement element) {
        print(stream,element.getClassName());
        print(stream,":");
        print(stream,element.getMethodName());
        print(stream," ");
        print(stream,"(");
        print(stream,element.getFileName());
        print(stream,":");
        print(stream,element.getLineNumber());
        print(stream,")");
        printNewLine(stream);
    }

    private static void printNewLine(OutputStream stream) {
        print(stream, System.lineSeparator());
    }

    private static void printPrefix(DebugParams par) {
        if(par.prefix == null)
            return;
        print(par.stream,par.prefix);
        print(par.stream," ");
    }

    private static void printOutput(DebugParams params, Object... output) {
        for(int i = 0; i < output.length; i++) {
            Object object = output[i];
            if(i > 0)
                print(params.stream, " ");
            print(params.stream, object);
        }
    }
    private static void setPrefix(DebugParams params, String prefix) {
        params.prefix = prefix;
    }

    private static void setEnabled(DebugParams params, boolean enabled) {
        params.classEnable = enabled;
    }

    public static class Instance  {
        final DebugParams params;
        public Instance(DebugParams par) {
            params = new DebugParams(par);
        }
        public Instance() {
            params = new DebugParams();
        }
        public void setOutputStream(OutputStream outputStream) {
            Debug.setOutPutStream(params,outputStream);
        }
        public void setPrefix(String prefix) {
            Debug.setPrefix(params,prefix);
        }
        public void d(Object... output) {
            Debug.log(params, output);
        }
        public void setDebugInfoNone() {
            Debug.setDebugInfo(params,DebugInfo.NONE);
        }
        public void setDebugInfoFileAndLine() {
            Debug.setDebugInfo(params,DebugInfo.LINE);
        }
        public void setDebugInfoFullTrace() {
            Debug.setDebugInfo(params,DebugInfo.TRACE);
        }
        public void setAllowedPattern(String regex) {
            Debug.setAllowedPattern(params,regex);
        }

        public void setEnabled(boolean enabled) {
            Debug.setEnabled(params,enabled);
        }

        public void setShowTime(boolean enabled) {
            Debug.setShowTime(params,enabled);
        }

        public void allowedClasses(Class... classes) {
            Debug.allowClasses(params, classes);
        }

        public void toFile(String path) {
            this.setOutputStream(new ToFile(path));
        }
        public void allowAllClasses() {
            Debug.allowAllClasses(params);
        }
    }
    private static class ToFile extends OutputStream {
        final File file;
        public ToFile(String path) {
            file = new File(path);
            file.delete();
        }

        @Override
        public void write(int i) throws IOException {
            throw new UnsupportedOperationException("write(int i) not supported");
        }

        @Override
        public void write(byte [] bytes) {
            try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file,true))) {
                bos.write(bytes);
                bos.close();
            } catch (IOException e) {
                RuntimeException ex = new RuntimeException("Unable to write to file " + file.getAbsolutePath());
                ex.initCause(e);
                throw ex;
            }
        }
    }
}
