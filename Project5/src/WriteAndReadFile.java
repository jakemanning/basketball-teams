import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;


public class WriteAndReadFile 
{
	private DataBaseModel model;
	
	private String fileName;
	

	public WriteAndReadFile(DataBaseModel model,String fileName, boolean write) throws ClassNotFoundException,IOException
	{
		
		
		this.fileName = fileName;
		
		if(write)
		{
			this.model = model;
			writeFile();
		}
		else
		{
			readFile();
		}
	}
	
	public void writeFile() throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName)); 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); 
		objectOutputStream.writeObject(this.model);
		objectOutputStream.writeObject(DataBaseModel.getJListOfPersons());
		objectOutputStream.writeObject(DataBaseModel.getJListOfStates());
		objectOutputStream.writeObject(DataBaseModel.getJListOfCities());
		
		objectOutputStream.writeObject(DataBaseModel.getJListOfIdentifications());

		objectOutputStream.close();
		
		System.out.println("Saved to " + fileName);
	}

	@SuppressWarnings("unchecked")
	public void readFile() throws ClassNotFoundException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream(new File(fileName));
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); 
		
		this.model = (DataBaseModel)objectInputStream.readObject();
		
		DataBaseModel.setJListOfPeople((Map<String,Person>)objectInputStream.readObject());
		DataBaseModel.setJListOfStates((Map<String,State>)objectInputStream.readObject());
		DataBaseModel.setJListOfCities((Map<String,City>)objectInputStream.readObject());
		DataBaseModel.setJListOfIdentifications((Map<String,Identification>)objectInputStream.readObject());
		objectInputStream.close();
	}
	
	public DataBaseModel getModel()
	{
		return this.model;
	}
}
