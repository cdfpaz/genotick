package com.alphatica.genotick.processor;

import com.alphatica.genotick.genotick.Prediction;
import com.alphatica.genotick.genotick.ProgramData;
import com.alphatica.genotick.instructions.*;
import com.alphatica.genotick.population.Program;
import com.alphatica.genotick.population.ProgramExecutor;
import com.alphatica.genotick.population.ProgramExecutorSettings;

public class SimpleProcessor extends Processor implements ProgramExecutor {

    private static final int MAX_JUMP = 255;
    private double[] registers;
    private Program program;
    private ProgramData data;
    private Double programResult;
    private InstructionList instructionList;
    private boolean terminateInstructionList;
    private int changeInstructionPointer;
    private boolean newJump;
    private int totalInstructionExecuted;
    private int instructionLimitMultiplier;
    private int programInstructionLimit;
    private int dataMaximumOffset;

    /**
     * Creates SimpleProcessor instance and initializes it.
     * @return Instance of SimpleProcessor.
     */
    public static SimpleProcessor createProcessor() {
        return new SimpleProcessor();
    }

    @Override
    public Prediction executeProgram(ProgramData programData, Program program) {
        prepare(programData, program);
        programInstructionLimit = program.getLength() * instructionLimitMultiplier;
        try {
            return executeProgramMain();
        } catch (NotEnoughDataException |
                TooManyInstructionsExecuted |
                ArithmeticException ex) {
            return Prediction.OUT;
        }
    }

    @Override
    public void setSettings(ProgramExecutorSettings settings) {
        registers = new double[totalRegisters];
        programResult = null;
        instructionLimitMultiplier = settings.instructionLimit;
    }

    private void prepare(ProgramData programData, Program program) {
        this.program = program;
        this.data = programData;
        programResult = null;
        instructionList = null;
        terminateInstructionList = false;
        changeInstructionPointer = 0;
        newJump = false;
        totalInstructionExecuted = 0;
        dataMaximumOffset = program.getMaximumDataOffset();
        zeroOutRegisters();
    }

    private void zeroOutRegisters() {
        for(int i = 0; i < registers.length; i++) {
            registers[i] = 0.0;
        }
    }

    private Prediction executeProgramMain()  {
        executeInstructionList(program.getMainFunction());
        if (programResult != null) {
            return Prediction.getPrediction(programResult);
        } else {
            return Prediction.getPrediction(registers[0]);
        }
    }

    private void executeInstructionList(InstructionList list)  {
        list.zeroOutVariables();
        //Debug.d("Starting to execute instruction list, size",list.getSize());
        terminateInstructionList = false;
        int instructionPointer = 0;
        do {
            //Debug.d("Instruction pointer = ",instructionPointer);
            Instruction instruction = list.getInstruction(instructionPointer++);
            instructionList = list; /* Set this every time - it could have been changed by recursive function call */
            //Debug.d("Executing instruction number",totalInstructionExecuted);
            //Debug.d(dumpObject(instruction));
            //Debug.d("Executing",instruction.getClass().getCanonicalName(),"pointer",instructionPointer);
            instruction.executeOn(this);
            totalInstructionExecuted++;
            if(totalInstructionExecuted > programInstructionLimit) {
                throw new TooManyInstructionsExecuted();
            }
            if(newJump) {
                newJump = false;
                int newPointer = instructionPointer + changeInstructionPointer;
                if(newPointer > instructionList.getSize())
                    newPointer = instructionPointer + 1;
                if(newPointer < 0)
                    newPointer = 0;
                instructionPointer = newPointer;
            }
            //Debug.d("Terminate",terminateInstructionList,"programResult",programResult);
        } while (!terminateInstructionList && programResult == null);
    }


    private SimpleProcessor() {
    }

    @Override
    public void execute(SwapRegisters ins) {
        int reg1 = ins.getRegister1();
        int reg2 = ins.getRegister2();
        double tmp = registers[reg1];
        registers[reg1] = registers[reg2];
        registers[reg2] = tmp;
    }

    @Override
    public void execute(IncrementRegister ins) {
        int reg = ins.getRegister();
        registers[reg]++;
    }

    @Override
    public void execute(MoveDoubleToRegister ins) {
        int reg = ins.getRegister();
        registers[reg] = ins.getDoubleArgument();
    }

    @Override
    public void execute(AddDoubleToRegister ins) {
        int reg = ins.getRegister();
        registers[reg] += ins.getDoubleArgument();
    }

    @Override
    public void execute(ZeroOutRegister ins) {
        int reg = ins.getRegister();
        registers[reg] = 0;
    }

    @Override
    public void execute(ReturnRegisterAsResult ins) {
        int reg = ins.getRegister();
        programResult = registers[reg];
    }

    @Override
    public void execute(@SuppressWarnings("unused") TerminateInstructionList ins) {
        terminateInstructionList = true;
    }

    @Override
    public void execute(DecrementRegister ins) {
        int reg = ins.getRegister();
        registers[reg]--;
    }

    @Override
    public void execute(MoveRegisterToRegister ins) {
        int reg1 = ins.getRegister1();
        int reg2 = ins.getRegister2();
        registers[reg1] = registers[reg2];
    }

    @Override
    public void execute(MoveVariableToRegister ins) {
        int reg = ins.getRegister();
        registers[reg] = instructionList.getVariable(ins.getVariableArgument());
    }

    @Override
    public void execute(MoveRegisterToVariable ins) {
        int reg = ins.getRegister();
        instructionList.setVariable(ins.getVariableArgument(), registers[reg]);
    }

    @Override
    public void execute(MultiplyRegisterByRegister ins) {
        int reg1 = ins.getRegister1();
        int reg2 = ins.getRegister2();
        registers[reg1] *= registers[reg2];
    }

    @Override
    public void execute(MultiplyRegisterByVariable ins) {
        int reg = ins.getRegister();
        registers[reg] *= instructionList.getVariable(ins.getVariableArgument());
    }

    @Override
    public void execute(MultiplyVariableByVariable ins) {
        double var1 = instructionList.getVariable(ins.getVariable1Argument());
        double var2 = instructionList.getVariable(ins.getVariable2Argument());
        instructionList.setVariable(ins.getVariable1Argument(), var1 * var2);
    }

    @Override
    public void execute(SubtractDoubleFromRegister ins) {
        int reg = ins.getRegister();
        registers[reg] -= ins.getDoubleArgument();
    }

    @Override
    public void execute(MoveDoubleToVariable ins) {
        instructionList.setVariable(ins.getVariableArgument(), ins.getDoubleArgument());
    }

    @Override
    public void execute(DivideRegisterByRegister ins) {
        int reg1 = ins.getRegister1();
        int reg2 = ins.getRegister2();
        registers[reg1] /= registers[reg2];
    }

    @Override
    public void execute(DivideRegisterByVariable ins) {
        int reg = ins.getRegister();
        double var = instructionList.getVariable(ins.getVariableArgument());
        registers[reg] /= var;
    }

    @Override
    public void execute(DivideVariableByVariable ins) {
        double var1 = instructionList.getVariable(ins.getVariable1Argument());
        double var2 = instructionList.getVariable(ins.getVariable2Argument());
        instructionList.setVariable(ins.getVariable1Argument(), var1 / var2);
    }

    @Override
    public void execute(DivideVariableByRegister ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        int reg = ins.getRegister();
        instructionList.setVariable(ins.getVariableArgument(), var / registers[reg]);
    }

    @Override
    public void execute(ReturnVariableAsResult ins) {
        programResult = instructionList.getVariable(ins.getVariableArgument());
    }

    @Override
    public void execute(AddRegisterToRegister ins) {
        int reg1 = ins.getRegister1();
        int reg2 = ins.getRegister2();
        registers[reg1] += registers[reg2];
    }

    @Override
    public void execute(SubtractRegisterFromRegister ins) {
        int reg1 = ins.getRegister1();
        int reg2 = ins.getRegister2();
        //Debug.d("Sub reg1 = reg1 - reg2",reg1,reg2);
        //Debug.d("Values",registers[reg1],registers[reg2]);
        registers[reg1] -= registers[reg2];
        //Debug.d("Result",registers[reg1]);
    }

    @Override
    public void execute(MoveDataToRegister ins) {
        int reg = ins.getRegister();
        int offset = Math.abs(ins.getDataOffsetIndex() % dataMaximumOffset);
        registers[reg] = data.getData(ins.getDataTableIndex(), offset);
    }

    @Override
    public void execute(MoveDataToVariable ins) {
        int offset = Math.abs(ins.getDataOffsetIndex() % dataMaximumOffset);
        double value = data.getData(ins.getDataTableIndex(), offset);
        instructionList.setVariable(ins.getVariableArgument(),value);
    }

    @Override
    public void execute(MoveRelativeDataToRegister ins)  {
        int reg = ins.getRegister();
        int varOffset = getRelativeOffset(ins);
        registers[reg] = data.getData(ins.getDataTableIndex(), varOffset);
    }

    @Override
    public void execute(MoveRelativeDataToVariable ins) {
        int varOffset = getRelativeOffset(ins);
        double value = data.getData(ins.getDataTableIndex(), varOffset);
        instructionList.setVariable(ins.getVariableArgument(),value);
    }

    @Override
    public void execute(JumpTo ins) {
        jumpTo(ins.getAddress());
    }
    private void jumpTo(int jumpAddress) {
        changeInstructionPointer = (jumpAddress % MAX_JUMP);
    }

    @Override
    public void execute(JumpIfVariableGreaterThanRegister ins) {
        double register = registers[ins.getRegister()];
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable > register) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableLessThanRegister ins) {
        double register = registers[ins.getRegister()];
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable < register) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableEqualRegister ins) {
        double register = registers[ins.getRegister()];
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable == register) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableNotEqualRegister ins) {
        double register = registers[ins.getRegister()];
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(register != variable) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterEqualRegister ins) {
        double register1 = registers[ins.getRegister1()];
        double register2 = registers[ins.getRegister2()];
        if(register1 == register2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterNotEqualRegister ins) {
        double register1 = registers[ins.getRegister1()];
        double register2 = registers[ins.getRegister2()];
        if(register1 == register2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterGreaterThanRegister ins) {
        double register1 = registers[ins.getRegister1()];
        double register2 = registers[ins.getRegister2()];
        if(register1 > register2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterLessThanRegister ins) {
        double register1 = registers[ins.getRegister1()];
        double register2 = registers[ins.getRegister2()];
        if(register1 < register2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableGreaterThanVariable ins) {
        double variable1 = instructionList.getVariable(ins.getVariable1Argument());
        double variable2 = instructionList.getVariable(ins.getVariable2Argument());
        if(variable1 > variable2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableLessThanVariable ins) {
        double variable1 = instructionList.getVariable(ins.getVariable1Argument());
        double variable2 = instructionList.getVariable(ins.getVariable2Argument());
        if(variable1 < variable2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableEqualVariable ins) {
        double variable1 = instructionList.getVariable(ins.getVariable1Argument());
        double variable2 = instructionList.getVariable(ins.getVariable2Argument());
        if(variable1 == variable2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableNotEqualVariable ins) {
        double variable1 = instructionList.getVariable(ins.getVariable1Argument());
        double variable2 = instructionList.getVariable(ins.getVariable2Argument());
        if(variable1 != variable2) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableGreaterThanDouble ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable > ins.getDoubleArgument()) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableLessThanDouble ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable < ins.getDoubleArgument()) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableEqualDouble ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable == ins.getDoubleArgument()) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableNotEqualDouble ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable != ins.getDoubleArgument()) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterGreaterThanDouble ins) {
        double register = registers[ins.getRegister()];
        if(register > ins.getDoubleArgument())
            jumpTo(ins.getAddress());
    }

    @Override
    public void execute(JumpIfRegisterLessThanDouble ins) {
        double register = registers[ins.getRegister()];
        if(register < ins.getDoubleArgument())
            jumpTo(ins.getAddress());
    }

    @Override
    public void execute(JumpIfRegisterEqualDouble ins) {
        double register = registers[ins.getRegister()];
        if(register == ins.getDoubleArgument())
            jumpTo(ins.getAddress());
    }

    @Override
    public void execute(JumpIfRegisterNotEqualDouble ins) {
        double register = registers[ins.getRegister()];
        if(register != ins.getDoubleArgument())
            jumpTo(ins.getAddress());
    }

    @Override
    public void execute(JumpIfRegisterEqualZero ins) {
        double register = registers[ins.getRegister()];
        if (register == 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterNotEqualZero ins) {
        double register = registers[ins.getRegister()];
        if(register != 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterGreaterThanZero ins) {
        double register = registers[ins.getRegister()];
        if(register > 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfRegisterLessThanZero ins) {
        double register = registers[ins.getRegister()];
        if(register < 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableEqualZero ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable == 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableNotEqualZero ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable != 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableGreaterThanZero ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable > 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(JumpIfVariableLessThanZero ins) {
        double variable = instructionList.getVariable(ins.getVariableArgument());
        if(variable < 0.0) {
            jumpTo(ins.getAddress());
        }
    }

    @Override
    public void execute(NaturalLogarithmOfData ins) {
        int tableIndex = ins.getDataTableIndex();
        int tableOffset = Math.abs(ins.getDataOffsetIndex() % dataMaximumOffset);
        double value = Math.log(data.getData(tableIndex,tableOffset));
        registers[ins.getRegister()] = value;
    }

    @Override
    public void execute(NaturalLogarithmOfRegister ins) {
        double value = Math.log(registers[ins.getRegister2()]);
        registers[ins.getRegister1()] = value;
    }

    @Override
    public void execute(NaturalLogarithmOfVariable ins) {
        double value = Math.log(instructionList.getVariable(ins.getVariable2Argument()));
        instructionList.setVariable(ins.getVariable1Argument(),value);
    }

    @Override
    public void execute(SqRootOfRegister ins) {
        double value = Math.pow(registers[ins.getRegister2()], 0.5);
        registers[ins.getRegister1()] = value;
    }

    @Override
    public void execute(SqRootOfVariable ins) {
        double value = Math.pow(instructionList.getVariable(ins.getVariable2Argument()),0.5);
        instructionList.setVariable(ins.getVariable1Argument(),value);
    }

    @Override
    public void execute(SwapVariables ins) {
        double var1 = instructionList.getVariable(ins.getVariable1Argument());
        double var2 = instructionList.getVariable(ins.getVariable2Argument());
        instructionList.setVariable(ins.getVariable1Argument(), var2);
        instructionList.setVariable(ins.getVariable2Argument(), var1);
    }

    @Override
    public void execute(SubtractVariableFromRegister ins) {
        int reg = ins.getRegister();
        registers[reg] -= instructionList.getVariable(ins.getVariableArgument());
    }

    @Override
    public void execute(DivideRegisterByDouble ins) {
        int reg = ins.getRegister();
        registers[reg] /= ins.getDoubleArgument();
    }

    @Override
    public void execute(MultiplyRegisterByDouble ins) {
        int reg = ins.getRegister();
        registers[reg] *= ins.getDoubleArgument();
    }

    @Override
    public void execute(DivideVariableByDouble ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        double result = var / ins.getDoubleArgument();
        instructionList.setVariable(ins.getVariableArgument(), result);
    }

    @Override
    public void execute(MultiplyVariableByDouble ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        double result = var * ins.getDoubleArgument();
        instructionList.setVariable(ins.getVariableArgument(), result);
    }

    @Override
    public void execute(ZeroOutVariable ins) {
        instructionList.setVariable(ins.getVariableArgument(), 0.0);
    }

    @Override
    public void execute(IncrementVariable ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        var++;
        instructionList.setVariable(ins.getVariableArgument(), var);
    }

    @Override
    public void execute(DecrementVariable ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        var--;
        instructionList.setVariable(ins.getVariableArgument(), var);
    }

    @Override
    public void execute(AddDoubleToVariable ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        double result = var + ins.getDoubleArgument();
        instructionList.setVariable(ins.getVariableArgument(), result);
    }

    @Override
    public void execute(SubtractDoubleFromVariable ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        double result = var - ins.getDoubleArgument();
        instructionList.setVariable(ins.getVariableArgument(), result);
    }

    @Override
    public void execute(AddRegisterToVariable ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        int register = ins.getRegister();
        double result = var + registers[register];
        instructionList.setVariable(ins.getVariableArgument(), result);
    }

    @Override
    public void execute(SubtractRegisterFromVariable ins) {
        double var = instructionList.getVariable(ins.getVariableArgument());
        int register = ins.getRegister();
        double result = var - registers[register];
        instructionList.setVariable(ins.getVariableArgument(), result);
    }

    @Override
    public void execute(AddVariableToVariable ins) {
        double var1 = instructionList.getVariable(ins.getVariable1Argument());
        double var2 = instructionList.getVariable(ins.getVariable2Argument());
        double result = var1 + var2;
        instructionList.setVariable(ins.getVariable1Argument(), result);
    }

    @Override
    public void execute(SubtractVariableFromVariable ins) {
        double var1 = instructionList.getVariable(ins.getVariable1Argument());
        double var2 = instructionList.getVariable(ins.getVariable2Argument());
        double result = var1 - var2;
        instructionList.setVariable(ins.getVariable1Argument(),result);
    }

    @Override
    public void execute(MoveVariableToVariable ins) {
        double var = instructionList.getVariable(ins.getVariable2Argument());
        instructionList.setVariable(ins.getVariable1Argument(),var);
    }

    private int getRelativeOffset(DataInstruction ins) {
        int variable = (int)Math.round(instructionList.getVariable(ins.getDataOffsetIndex()));
        //noinspection UnnecessaryLocalVariable
        int offset = Math.abs(variable % dataMaximumOffset);
        return offset;
    }
}
