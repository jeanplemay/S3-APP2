package e04p3;

import java.util.Vector;

public class Invoker {

	private FactoryController fController;
	private int currentCommand;
	private Vector<Command> commandList;
	
	public Invoker(FactoryController fController) {
		this.fController = fController;
		this.currentCommand = -1;
		this.commandList = new Vector<Command>();
	}

	public FactoryController getfController() {
		return fController;
	}

	public void setfController(FactoryController fController) {
		this.fController = fController;
	}

	public int getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(int currentCommand) {
		this.currentCommand = currentCommand;
	}

	public Vector<Command> getCommandList() {
		return commandList;
	}

	public void setCommandList(Vector<Command> commandList) {
		this.commandList = commandList;
	}
	
	public void addCommand(Command command){
//		for(int i=commandList.size(); i>currentCommand-1; i-- )
//		{
//			commandList.remove(i);
//		}
		commandList.add(command);
		currentCommand++;
		System.out.println(currentCommand);
	}
	
	
	public void undo() {
		if(currentCommand >= 0) {
			commandList.get(currentCommand).unexecute();
			currentCommand--;
		}
		System.out.println(currentCommand);

	}
	
	public void redo() {
		if(currentCommand < commandList.size()-1) {
			commandList.get(currentCommand+1).execute();
			currentCommand++;
		}
		System.out.println(currentCommand);

	}
	
	
	
	
}
