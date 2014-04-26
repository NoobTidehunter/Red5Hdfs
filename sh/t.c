#include <stdio.h>

void print();

int main () {
  int i = 0, j = 0;
  for (i = 0; i < 10000; i++) {
    for (j = 0; j < 10000; j++) {
      print();
    }    
  }

  return 0;
}

void print() {
  printf("test ---------->-----------------");
}
