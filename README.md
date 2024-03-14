# Setup

Download from https://www.graalvm.org/latest/reference-manual/llvm/

```shell
export LLVM_TOOLCHAIN=llvm-23.1.1-macos-aarch64/lib/sulong/native/bin
$LLVM_TOOLCHAIN/clang++ -shared src/main/cpp/lib.cpp -lgraalvm-llvm -o lib.so
```

Also set JVM to `llvm-23.1.1-macos-aarch64/jvm`.

# C++

```c++
std::vector<int> *allocX() {
    std::vector<int>* x = new std::vector<int>();
    return x;
}

std::vector<int> *add(std::vector<int>* x, int v) {
    x->push_back(v);
    return x;
}

void printX(std::vector<int>* x) {
    for (int i = 0; i < x->size(); i++)
        std::cout << x->at(i) << "\n";
}
```

# Java

```java
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
```

# Result

```java
1
2
3
```

Even comes complete with "type hints":

![image](https://github.com/makslevental/graalvm_cpp_interop/assets/5657668/e63c4b85-73c5-4ab1-ab4f-9af260434de8)


# TODO

[Kotlin version](https://github.com/elide-dev/elide/blob/8716fd3c5c71f0d28d14581e47a6ec5b6a0a8423/packages/graalvm-py/src/main/kotlin/elide/runtime/plugins/python/Python.kt)
