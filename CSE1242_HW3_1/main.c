//This class evaluate the ears of bunny according to some cases.
#include <stdio.h>

int currentLine =0; // currentLine indicates the line right now.
int sumOfEars = 0;  // sumOfEars indicates the total ears of bunny.
int line;
void evaluateBunnyEars(int numbOfLines){
    if(numbOfLines +1){
        //use switch for the odd case and even bunny case.
        switch (currentLine%2) {
            case 0:
                if(currentLine!=0){
                    sumOfEars+=3; //add 3 to total ears ,if even and not zero line.
                }
                break;
            case 1: sumOfEars +=2;  //add 2 to total ears ,if odd.
                break;
        }
        printf("bunnyEars%d(%d) → %d\n" , line, currentLine,sumOfEars); //print on the screen the total ears according to line.
        currentLine++; //pass the next line.
        evaluateBunnyEars(numbOfLines-1); //call this method again and again until finish the lines.
    }
}

int main(void) {
    int numbOfLines;
    printf("Please enter the number of lines (n=):");
    scanf("%d", &numbOfLines); //get number of lines information from the user.
    line = numbOfLines; //hold the line information to add bunny name.
    evaluateBunnyEars(numbOfLines); //to evaluate total ears, call evaluateBunnyEars method.
}
