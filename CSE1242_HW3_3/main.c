//This class draw the triangles according to given iteration number from the user.
#include <stdio.h>

int row = 1;
int column = 1;
//this method draw the all triangles.
void  drawTriangle(int space, int x1,int y1,int x2,int y2, int x3, int y3){
    if(row < 33 && row <= y2){
        if(column < 64 && column <= x3){
            int startPoint = x2 + space;
            int finishPoint= x3 - space;
            if(column < startPoint){
                printf("_");
            }
            else if(column > finishPoint ){
                printf("_");
            }
            else{
                printf("1");
            }
            column++;
            drawTriangle( space, x1, y1, x2, y2,  x3, y3);
        }
        else{
            printf("\n");
            space--;
            column=1;
            row++;
            drawTriangle( space, x1, y1, x2, y2,  x3, y3);
        }
    }
}


//this method find to middle point of two points.
void findMidPoint(int typeOfTriangle,int x1,int y1,int x2,int y2, int x3, int y3){
    if(typeOfTriangle>0){
        int Downx12 = (x1 + x2)/2;
        int Upx12 = (x1 + x2)/2 +1;
        int Upy12 = (y1 + y2)/2;
        int Downy12 = (y1 + y2)/2 +1;

        int Upx13 = (x1 + x3)/2;
        int Downx13 = (x1 + x3)/2 +1;
        int Upy13 = (y1 + y3)/2;
        int Downy13 = (y1 + y3)/2 +1;

        int Leftx23 = (x2 + x3)/2-1;
        int Rightx23 = (x2 + x3)/2+1;
        int y23 = (y2 + y3)/2;

        //to find points of the corner of all triangles, use the recursion way.
        findMidPoint(typeOfTriangle-1, x1,y1,Upx12,Upy12,Upx13,Upy13);
        findMidPoint(typeOfTriangle-1, Downx12,Downy12,x2,y2,Leftx23,y23);
        findMidPoint(typeOfTriangle-1, Downx13,Downy13,Rightx23,y23,x3,y3);
    }
    else{
        int space = (x3-x2)/2;
        drawTriangle(space, x1,y1,x2,y2,x3,y3); //cal the drawTriangle method two show all triangles on the screen.
    }
}

int main() {
    int typeOfTriangle; // typeOfTriangle hold the type of triangle.
    scanf("%d", &typeOfTriangle); //take the information of triangle type.

    //there are the initial corner coordinate of triangle. below
    int x1 = 32;
    int y1 = 1;
    int x2 = 1;
    int y2 = 32;
    int x3 = 63;
    int y3 = 32;

    findMidPoint(typeOfTriangle,x1,y1,x2,y2,x3,y3); //cal the method to find middle point of itial points.
    return 0;
}
