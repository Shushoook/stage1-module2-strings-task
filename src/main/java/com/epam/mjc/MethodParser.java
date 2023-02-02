package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        System.out.println(signatureString);
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String args = signatureString.substring(signatureString.indexOf("(") + 1, signatureString.indexOf(")"));
        String[] argsPar = args.split("[ ,]+");
        int argLength = argsPar.length;
        System.out.println(argLength);
        if (argLength > 1) {
            for (int i = 0; i < argsPar.length; i += 2) {
                String type = argsPar[i];
                String name = argsPar[i + 1];
                arguments.add(new MethodSignature.Argument(type, name));
            }
        }

        String name;
        String accessModifier;
        String returnType;

        String[] start = signatureString.substring(0, signatureString.indexOf("(")).split(" ");
        if (start.length == 3) {
            accessModifier = start[0];
            returnType = start[1];
            name = start[2];
        } else {
            accessModifier = null;
            returnType = start[0];
            name = start[1];
        }
        MethodSignature signature = new MethodSignature(name, arguments);
        signature.setAccessModifier(accessModifier);
        signature.setReturnType(returnType);
        return signature;
    }
}
