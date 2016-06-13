package bitman.fictionalvieira.bash;

public class RedirectOperator implements BashCommand {
	private final String file;
	private final BashCommand cmd;
	private final Type type;

	public RedirectOperator(BashCommand cmd, String file, RedirectOperator.Type type) {
		this.cmd = cmd;
		this.file = file;
		this.type = type;
	}
	@Override
	public String getCode() {
		return cmd + " " + type.getSymbol() + " " + file;
	}

	public enum Type {
		OVERWRITE(">"),
		APPEND(">>");

		private final String symbol;

		Type(String symbol) {
			this.symbol = symbol;
		}

		public String getSymbol() {
			return symbol;
		}
	}
}
