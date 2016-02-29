package Research;

public class SuggestionSorter {
     
	int[][] dataBase;
	
	public void DataBase(){
		int[][] dataBase = new int[5][5];
		
	}
	/**
	 * for a certain movieID, find the average of all the ratings for that movie 
	 * @return
	 * the average
	 */
	public int Average(int[][] Data){
		dataBase = Data; //not sure if this copies the values. do we need to do it in a loop?
		int rating;
		int sum = 0;
		int k = 0;
		for (int i = 0; i < 5; i++){
			   for(int j = 0; j<5; j++){
					// the current sum of ratings for that movie divided by 25 will give us the average.
					rating = Data[i][j];
					sum += rating;
				}
		}
	
		return sum/25;
		
	}
	/**
	 * output the suggestions according to ratings 
	 * @return
	 * the suggestions
	 */
	public int Suggestions(){
		for (int i = 0; i < 5; i++){
			   for(int j = 0; j<5; j++){
				   if(Average(dataBase) >= 3){
						return data;
					}
			   }
		}
		
			
	}
}
