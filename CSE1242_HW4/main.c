// This program suggests a route to get from a starting point to a target point on a metro line.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <Math.h>
#define SIZE 10

//structs are composed according to i̇nstructions in HW pdf.
typedef struct MetroStation{
    char name[20];
    double x;
    double y;
}MetroStation;

typedef struct MetroLine{
    char color[20];
    MetroStation MetroStations[SIZE];
}MetroLine;

typedef struct MetroSystem{
    char name[20];
    MetroLine MetroLines[SIZE];
}MetroSystem;

//Compose a system, which is named "istanbul, and empty MetroStation
MetroSystem istanbul = {"istanbul", '\0'};
MetroStation empty = {'\0'};

//This function returns a non-zero value if the name property of the MetroStation s1 is equal to the name property of MetroStation s2; zero otherwise.
int equals(MetroStation s1, MetroStation s2){
    if(strcmp(s1.name,s2.name)== 0){
        return 1;
    }
    else
        return 0;
}

//This function adds the given metro station to the end of the MetroStations[]
void addStation(MetroLine *l1, MetroStation s1){
    int index =0;
    //This for loop allows us to know how many index of array is filled.
    for(; strcmp(l1->MetroStations[index].name, "")!=0; index++){
    }
    l1->MetroStations[index] = s1;
}

// This function returns a non-zero value if the given metro line has a metro station with the same name as the given metro station; zero otherwise.
int hasStation(MetroLine l1, MetroStation s1){
    int i=0;
    for(; i< strcmp(l1.MetroStations[i].name,"") !=0; i++){
        if(strcmp(l1.MetroStations[i].name, s1.name)){
            return 1;
        }
    }
    return 0;
}

//This function returns the MetroStation representing the first stop of the given metro line. If there is no such station, your function should return an empty MetroStation
MetroStation getFirstStop(MetroLine l1){
    if(strcmp(l1.MetroStations[0].name , "")){
        return empty; //if the array is empty, return an empty array.
    }
    else{
        return l1.MetroStations[0];
    }
}
//This function returns the previous MetroStation to the MetroStation passed as input. If the given station is the first stop on the MetroLine, then this function
//should return an empty MetroStation. You may assume that there are no “loops” in the MetroLine that is, no station is present on the same line twice
MetroStation getPreviousStop(MetroLine l1, MetroStation s1){
    int i= 0;
    for(; strcmp(l1.MetroStations[i].name,"") !=0; i++){
        if(equals(l1.MetroStations[i], s1)){
            if(i==0){
                return empty; //if the index of metrostation is stating of the array, return an empty MetroStation.
            }
            else
                return l1.MetroStations[i-1];
        }
    }
    return empty;
}
// This function returns the MetroStation after the MetroStation passed as input. If the given station is the last stop on the MetroLine, then this function should
//return an empty MetroStation.
MetroStation getNextStop(MetroLine l1, MetroStation s1){
    int index=0;
    for(; strcmp(l1.MetroStations[index].name, "")!=0; index++){
    }
    int i;
    for(; strcmp(l1.MetroStations[i].name,"") !=0; i++) {
        if (equals(l1.MetroStations[i], s1)) {
            if(index==i){
                return empty; //if the index of metrostation is last one, return an empty MetroStation.
            }
            else
                return l1.MetroStations[i+1];
        }
    } return empty;
}
//This function adds the given metro line to the end of the MetroLines[] array pointed by MetroSystem pointer.
void addLine(MetroSystem *s1, MetroLine l1){
    int index=0;
    //Take the last index by using for loop
    for(; strcmp(s1->MetroLines[index].color, "")!=0; index++){
    }
    s1->MetroLines[index] = l1;
}
//This function  prints the metro stations of the given metro line.
void printLine(MetroLine l1){
    int index = 0;
    //Take the size of index, which is not null ,by using for loop
    for(; strcmp(l1.MetroStations[index].name, "")!=0; index++){
    }
    printf("MetroLine %s: ", l1.color);
    int i=0;
    for(; i<index;i++){
        if(i<index-1)
            printf("%s, ",l1.MetroStations[i].name);
        else
            printf("%s.\n",l1.MetroStations[i].name);
    }
}
// This function prints the metro stations in the given array
void printPath(MetroStation MetroStations[]){
    int i = 0;
    for(; i< SIZE;i++){
        if(strcmp(MetroStations[i].name, "")){
            printf("%d. %s\n", i+1,MetroStations[i].name);
        }
    }

}
//This function calculates the distance between two points.
double distance(double x1,double y1, double x2, double y2){
    return sqrt(pow(x1-x2, 2) + pow(y1-y2, 2));
}
//This function returns a double value representing the total distance travelled along a path that goes from the first MetroStation in the array
double getDistanceTravelled(MetroStation s1[]){
    double path= 0;
    int index =0;

    int i=1;
    //calculate total distance between start and finish point.
    for(; strcmp(s1[i].name, "") !=0;i++){
        path += distance(s1[i].x , s1[i].y, s1[i - 1].x, s1[i - 1].y);
        index++;

    }
    if(index<2)
        return 0.0;
    else
        return path;
}
//This function returns the MetroStation which is nearest to the x and y
MetroStation findNearestStation(MetroSystem s1,double x, double y){
    int smallestDistance = 1000000000;
    MetroStation st = {'\0'};
    MetroStation nearSt= {'\0'};
    int i=0;
    for(; strcmp(s1.MetroLines[i].color,"") !=0;i++) {
        int j=0;
        for (; strcmp(s1.MetroLines[i].MetroStations[j].name,"") !=0; j++) {
            st = s1.MetroLines[i].MetroStations[j];
            double dist = distance(x, y, st.x, st.y); //Call distance function to know the distance of MetroStation from x and y
            if(dist<smallestDistance){
                smallestDistance = dist;
                nearSt = st;
            }
        }
    }return nearSt;
}

//This function controls whether there is in the array.
int exist(MetroStation arr[], MetroStation st){
    int i = 0;
    for (; strcmp(arr[i].name,"") != 0; i++) {
        if(strcmp(arr[i].name,st.name) ==0){
            return 1;
        }
    } return 0;
}

//This function fills the given neigboringStations array containing all neighboring stations to the given station (possibly many if the station is on many lines)
void getNeighboringStations(MetroSystem s1, MetroStation st1,MetroStation neighboringStations[] ) {
    int index=0;
    int i=0;
    //Take the size of MetroStation index in each line, which is not null ,by using for loop
    for(; strcmp(s1.MetroLines[i].color, "") != 0; i++){
        for(; strcmp(s1.MetroLines[i].MetroStations[index].name, "")!=0; index++){
        }
    }
    int count = 0;
    int j = 0;
    for (; strcmp(s1.MetroLines[j].color,"") !=0; j++) {
        int y = 0;
        for (; strcmp(s1.MetroLines[j].MetroStations[y].name,"") != 0; y++) {

            if (strcmp(s1.MetroLines[j].MetroStations[y].name, st1.name) == 0) {
                if (y != 0 && exist(neighboringStations,s1.MetroLines[j].MetroStations[y - 1])==0 ) { //call the exist function to determine whether the metro station that will be added is already exist in this array.
                    neighboringStations[count] = s1.MetroLines[j].MetroStations[y - 1];
                    count++;
                }
                if (y != index - 1 && exist(neighboringStations,s1.MetroLines[j].MetroStations[y + 1])==0 ) {
                    neighboringStations[count] = s1.MetroLines[j].MetroStations[y + 1];
                    count++;
                }
            }
        }
    }
}

//This function fill the content of the bestPath which contain full path that goes from start until finish.
float smallestPath =1000000000000000;
void recursiveFindPath(MetroStation start, MetroStation finish, MetroStation partialPath[], MetroStation bestPath[]) {

    int i = 0;
    //if there is a back, the path is ended.
    for (; strcmp(partialPath[i].name, "") != 0; i++) {
        if (strcmp(partialPath[i].name, start.name) == 0){
            return;
        }
    }

    //if path can reach the finish point, the path is saved.
    if (equals(start, finish)) {

        //Learn the number of index that is not null
        int index=0;
        for(; strcmp(partialPath[index].name , "");index++){
        }
        partialPath[index] = start;

        //choose the best path according to total travelled distance.
        if(smallestPath> getDistanceTravelled(partialPath)){
            smallestPath = getDistanceTravelled(partialPath);
            int j= 0;
            //if the best path will be changed, the array should be emptied,because the previous array can be longer than new path.
            for(; strcmp(bestPath[j].name, "") != 0; j++){
                bestPath[j] = empty;
            }
            int i= 0;
            for (; strcmp(partialPath[i].name, "") !=0; i++) {
                bestPath[i] = partialPath[i];
            }
        }
        return;
    }
    MetroStation neighbors[SIZE] ={'\0'};
    getNeighboringStations(istanbul, start, neighbors); //HOCAYA MAİL AT


    //this function continue to reach a return statement (recursive function) by using this for loop.
    int a=0;
    for(; strcmp(neighbors[a].name, "") !=0; a++){

        MetroStation duplicatePath[SIZE] = {'\0'};
        int j=0;
        for (; strcmp(partialPath[j].name, "") !=0; j++) {
            duplicatePath[j] = partialPath[j];
        }
        duplicatePath[j]= start;
        recursiveFindPath(neighbors[a],finish,duplicatePath, bestPath);

    }
}

//This function compose an empty array and call the recursiveFindPath functions.
void findPath(MetroStation start, MetroStation finish, MetroStation path[]){
    MetroStation  partialPath[10] ={'\0'};
    recursiveFindPath(start, finish, partialPath, path);
}

int main()
{
    int i;
    double myX=1,
            myY=2;
    double goalX=62,
            goalY=45;

    // define 3 metro lines, 9 metro stations, and an empty myPath
    MetroLine red={'\0' }, blue={'\0'}, green={'\0'};
    MetroStation s1, s2, s3, s4, s5, s6, s7, s8, s9;
    MetroStation myPath[SIZE]={'\0'};

    strcpy(red.color, "red");
    strcpy(blue.color, "blue");
    strcpy(green.color, "green");


    strcpy(s1.name, "Haydarpasa"); 		s1.x=0; 	s1.y=0;
    strcpy(s2.name, "Sogutlucesme"); 	s2.x=10; 	s2.y=5;
    strcpy(s3.name, "Goztepe"); 		s3.x=20; 	s3.y=10;
    strcpy(s4.name, "Kozyatagi"); 		s4.x=30; 	s4.y=35;
    strcpy(s5.name, "Bostanci"); 		s5.x=45; 	s5.y=20;
    strcpy(s6.name, "Kartal"); 			s6.x=55; 	s6.y=20;
    strcpy(s7.name, "Samandira"); 		s7.x=60; 	s7.y=40;
    strcpy(s8.name, "Icmeler"); 		s8.x=70; 	s8.y=15;


    //Add several metro stations to the given metro lines.
    addStation(&red, s1); addStation(&red, s2); addStation(&red, s3); addStation(&red, s4); addStation(&red, s5); addStation(&red, s8);

    addStation(&blue, s2); addStation(&blue, s3); addStation(&blue, s4); addStation(&blue, s6); addStation(&blue, s7);

    addStation(&green, s2); addStation(&green, s3); addStation(&green, s5); addStation(&green, s6); addStation(&green, s8);

    // Add red, blue, green metro lines to the Istanbul metro system.
    addLine(&istanbul, red);
    addLine(&istanbul, blue);
    addLine(&istanbul, green);

    // print the content of the red, blue, green metro lines
    printLine(red);
    printLine(blue);
    printLine(green);


    // find the nearest stations to the current and target locations
    MetroStation nearMe = findNearestStation(istanbul, myX, myY);
    MetroStation nearGoal = findNearestStation(istanbul, goalX, goalY);

    printf("\n");

    printf("The best path from %s to %s is:\n", nearMe.name, nearGoal.name);

    // if the nearest current and target stations are the same, then print a message and exit.
    if(equals(nearMe, nearGoal)){
        printf("It is better to walk!\n");
        return 0;
    }

    // Calculate and print the myPath with the minimum distance travelled from start to target stations.
    findPath(nearMe, nearGoal, myPath);

    if(strlen(myPath[0].name) == 0)
        printf("There is no path on the metro!\n");
    else{
        printPath(myPath);
    }
    return 0;
}
