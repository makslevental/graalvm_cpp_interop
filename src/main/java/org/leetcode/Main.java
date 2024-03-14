package org.leetcode;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.File;
import java.io.IOException;

import static java.lang.Boolean.TRUE;

public class Main {
    public static void main(String[] args) throws IOException {
        try (var context = Context.newBuilder().option("llvm.C++Interop", TRUE.toString()).allowAllAccess(true).build()) {
            File f = new File("/Users/mlevental/dev_projects/graalvm/lib.so");
            var bc = context.eval(Source.newBuilder("llvm", f).build());
            var r = bc.invokeMember("allocX");
            bc.invokeMember("add", r, 1);
            bc.invokeMember("add", r, 2);
            bc.invokeMember("add", r, 3);
            bc.invokeMember("printX", r);
        }
    }
}