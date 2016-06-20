package guru.bitman.fictionalvieira.bash;

import java.util.ArrayList;
import java.util.Collections;

abstract class ChainOperator
		implements BashCommand{

	private ArrayList<BashCommand> cmd = new ArrayList<>();

	public ChainOperator(BashCommand... cmd) {
		Collections.addAll(this.cmd, cmd);
	}

	@Override
	public String getCode() {
		String code = "";

		int i = 0;
		for (BashCommand c : this.cmd) {
			code += c.getCode() + postCode(i);
			i++;
		}

		return code;
	}

	private String postCode(int i) {
		return i == this.cmd.size() - 1 ? "" : " " + getOperator() + " ";
	}

	protected abstract String getOperator();
}
