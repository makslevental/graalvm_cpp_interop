#include <graalvm/llvm/polyglot.h>
#include <vector>
#include <stdlib.h>
#include <iostream>

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
