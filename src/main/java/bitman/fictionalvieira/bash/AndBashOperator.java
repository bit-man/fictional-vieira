package bitman.fictionalvieira.bash;

import java.util.ArrayList;

public class AndBashOperator implements BashCommand {
	private final ArrayList<BashCommand> cmd = new ArrayList<>();

	public AndBashOperator(BashCommand... cmd) {
		for (BashCommand c : cmd) {
			this.cmd.add(c);
		}
	}

	@Override
	public String getCode() {
		String code = "";

		int i = 0;
		for (BashCommand c : this.cmd) {
			code += c.getCode() + ( i == this.cmd.size() - 1 ? "" : " && ");
			i++;
		}

		return code;
	}
}
