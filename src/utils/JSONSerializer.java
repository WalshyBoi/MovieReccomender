package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;


public class JSONSerializer implements Serializer {

	private Stack stack = new Stack();
	private File file;

	  public JSONSerializer(File file)
	  {
	    this.file = file;
	  }

	 
	 
	 
	@Override
	public void push(Object o) {
		// TODO Auto-generated method stub
		 stack.push(o);
		
	}

	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		 return stack.pop(); 
	}

	@Override
	public void write() throws Exception {
		 {
			    ObjectOutputStream os = null;

			    try
			    {
			      XStream xstream = new XStream(new DomDriver());
			      os = xstream.createObjectOutputStream(new FileWriter(file));
			      os.writeObject(stack);
			    }
			    finally
			    {
			      if (os != null)
			      {
			        os.close();
			      }
			    }
			  }
			}

		
	

	@Override
	public void read() throws Exception {
		
		  {
		    ObjectInputStream is = null;

		    try
		    {
		      XStream xstream = new XStream(new DomDriver());
		      is = xstream.createObjectInputStream(new FileReader(file));
		      stack = (Stack) is.readObject();
		    }
		    finally
		    {
		      if (is != null)
		      {
		        is.close();
		      }
		    }
		  }
		
	}

}
