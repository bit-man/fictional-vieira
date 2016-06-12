package bitman.fictionalvieira.command;

import java.io.IOException;
import java.io.Writer;

public class TestWriter extends Writer {
	private String str;

	public void write(String str) throws IOException {
		this.str = str;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {

	}
	@Override
	public void flush() throws IOException {

	}

	@Override
	public void close() throws IOException {

	}

	public String getOut() {
		return str;
	}
}
