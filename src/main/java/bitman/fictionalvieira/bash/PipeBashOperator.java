package bitman.fictionalvieira.bash;

public class PipeBashOperator
		extends ChainOperator
		implements BashCommand	{

	public PipeBashOperator(BashCommand... cmd) {
		super(cmd);
	}

	@Override
	protected String getOperator() {
		return "|";
	}
}
