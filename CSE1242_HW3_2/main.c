//this class adds digits of  the number and creates a new number until it matches the single digit number called "superDigit".
#include <stdio.h>

//this method evaluate the digits of number
int evaluateDigits(int number) {
    if (number != 0)
        return (number % 10 + evaluateDigits (number / 10));
    else
        return 0;
}

//this method control whether current number is superDigit or not.
void controlSuperDigit(int number){
    if(number>9){
        number = evaluateDigits(number);
        controlSuperDigit(number);
    }
    else
        printf(" is %d.", number);
}
//this method print the repetitive number.
void printNumber(int number, int repetition) {
    if(repetition){
        printf("%d", number);
        printNumber(number, repetition-1);
    }
}

int main() {
    int number; // number hold the number which enter on the screen.
    int repetition; // repetition hold the number which enter on the screen.
    printf("Please enter a number (n=) :");
    scanf("%d", &number); //take the number from user.

    printf("Please enter repetition factor (k=) :"); //take the repetition number from user.
    scanf("%d", &repetition);

    //start to print the result of operation
    printf("Super digit of number ");
    printNumber(number,repetition);

    int currentNmb = evaluateDigits(number) * repetition;
    controlSuperDigit(currentNmb); // call the control method to control whether digit is super digit ornot.

    return 0;
}
