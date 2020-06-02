package e04p3;

//This class makes part of the design pattern COMMAND with class FactoryController, Invoker, Command,
//AddShapeCommand, AddArrowCommand and MoveShape Command;
public abstract class Command {
	
	public abstract void execute();
	public abstract void unexecute();

}
