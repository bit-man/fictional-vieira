package guru.bitman.fictionalvieira.bash;

public class AndBashOperator
		extends ChainOperator
		implements BashCommand {

	public AndBashOperator(BashCommand... cmd) {
		super(cmd);
	}

	@Override
	protected String getOperator() {
		return "&&";
	}
}
