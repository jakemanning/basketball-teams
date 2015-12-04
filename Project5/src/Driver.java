
public class Driver 
{
	/**Instantiation of the Model, View, and Controller for the program.*/
	static DataBaseModel dataBaseModel;
	static MainView mainView = new MainView();
	static Controller theController = new Controller();
	
	public static void main(String args[])
	{
		dataBaseModel = new DataBaseModel();
		theController.setModel(dataBaseModel);
		theController.setMainView(mainView);
	}
}
