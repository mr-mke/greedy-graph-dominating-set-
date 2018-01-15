import java.util.*;

public class gds{

    public static void main(String[] args){
        int number_of_nodes;
        int adjacency_matrix[][];
        int degrees[][];
        int i,j;
        int status[];
        int coloured=0;
        int current_node;
        int current_degree;
        int domination_number=0;
        int all_coloured=0;

        Scanner scanner = new Scanner(System.in);
     	try{
    	    System.out.println("Enter the number of nodes in the graph");
            number_of_nodes = scanner.nextInt();

    	    adjacency_matrix = new int[number_of_nodes][number_of_nodes];
            degrees = new int[number_of_nodes][2];
            status= new int[number_of_nodes];
            current_degree=number_of_nodes-1;

            // scan matrix and calculate dgrees
       	    System.out.println("Enter the adjacency matrix");
    	    for (i = 0; i < number_of_nodes; i++){
                degrees[i][0]=i;
                for (j = 0; j < number_of_nodes; j++){
                    adjacency_matrix[i][j] = scanner.nextInt();
                    degrees[i][1] = degrees[i][1] + adjacency_matrix[i][j]; 
                }
            }

            // sorting degrees           
            quickSort(degrees,0,number_of_nodes-1);

            while(coloured < number_of_nodes){
                current_node=degrees[current_degree][0];
                all_coloured=1;
                for(i=0;i<number_of_nodes;i++){
                    if(adjacency_matrix[current_node][i]==1 && status[i] == 0){
                        status[i]=2;
                        coloured++;
                        all_coloured=0;
                    }
                }
                //if all neighboures colored do not do anything
                if(all_coloured==0){
                    //if it is coloured do not change the coloured count
                    if(status[current_node]==0)
                        coloured++;
                    status[current_node]=1;
                    domination_number++;
                }
                System.out.println("");
                for(i=0;i<number_of_nodes;i++)
                    System.out.println((degrees[i][0] + 1) + " - " + degrees[i][1] + " - " + status[degrees[i][0]]);
                System.out.println("");
                System.out.println("coloured: "+coloured);
                System.out.println("");

                current_degree--;
            }
            
            System.out.print("\nThe dominating set is: ");
            for(i=0;i<number_of_nodes;i++){
                if(status[i]==1)
                    System.out.print((i+1) +" ");
            }
            System.out.println("");

            System.out.println("The dominatation number is: "+ domination_number);


        }catch(InputMismatchException inputMismatch){
            System.out.println("Wrong Input format");
        }	
        scanner.close();	
    }

    static int partition(int arr[][], int left, int right){
        int i = left, j = right;
        int tmp0,tmp1;
        int pivot = arr[(left + right) / 2][1];
        while (i <= j) {
            while (arr[i][1] < pivot)
                i++;
            while (arr[j][1] > pivot)
                j--;
            if (i <= j) {
                tmp0 = arr[i][0];
                arr[i][0] = arr[j][0];
                arr[j][0] = tmp0;
                tmp1 = arr[i][1];
                arr[i][1] = arr[j][1];
                arr[j][1] = tmp1;
                i++;
                j--;
            }
        }
        return i;
    }

    static void quickSort(int arr[][], int left, int right) {
          int index = partition(arr, left, right);
          if (left < index - 1)
                quickSort(arr, left, index - 1);
          if (index < right)
                quickSort(arr, index, right);
    }


}

