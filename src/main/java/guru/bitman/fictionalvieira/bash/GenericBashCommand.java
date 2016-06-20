package guru.bitman.fictionalvieira.bash;

public class GenericBashCommand
	implements BashCommand {

	private final String cmdPlusArgs;

	public GenericBashCommand(String cmdPlusArgs) {
		this.cmdPlusArgs = cmdPlusArgs;
	}

	@Override
	public String getCode() {
		return cmdPlusArgs;
	}
}
