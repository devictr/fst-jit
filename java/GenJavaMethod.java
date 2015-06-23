package generated;
public class GenJavaMethod {
	public static float compute(int[] token) {
		int pos=0;
		float result=0f;
		return state_16(token, pos, result);
	}

	private static float state_16(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 65:
				if(pos==token.length) {return result;}
				return state_15(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_15(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 97:
				return state_14(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_14(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 110:
				return state_1(token, pos, result);
			case 114:
				return state_13(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_1(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 105:
				if(pos==token.length) {return result;}
				return (pos!=token.length) ? -1 : result;
			default:
				return -1;
		}
	}

	private static float state_13(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 111:
				return state_11(token, pos, result);
			case 117:
				result+=8f;
				if(pos==token.length) {return result;}
				return (pos!=token.length) ? -1 : result;
			default:
				return -1;
		}
	}

	private static float state_11(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 110:
				if(pos==token.length) {return result;}
				return state_10(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_10(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 105:
				return state_9(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_9(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 99:
				if(pos==token.length) {return result;}
				return state_4(token, pos, result);
			case 116:
				return state_8(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_4(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 97:
				result+=5f;
				return state_3(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_3(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 108:
				if(pos==token.length) {return result;}
				return (pos!=token.length) ? -1 : result;
			default:
				return -1;
		}
	}

	private static float state_8(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 101:
				if(pos==token.length) {return result;}
				return (pos!=token.length) ? -1 : result;
			case 105:
				result+=7f;
				return state_7(token, pos, result);
			default:
				return -1;
		}
	}

	private static float state_7(int[] token, int pos, float result) {
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 99:
				if(pos==token.length) {return result;}
				return (pos!=token.length) ? -1 : result;
			default:
				return -1;
		}
	}
}
