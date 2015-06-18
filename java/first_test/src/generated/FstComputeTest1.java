class FstComputeTest1 {
	public static float compute(int[] token) {
		int pos=0;
		float result=0f;
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 77:
				if(pos>=token.length) {return -1;}
				switch(token[pos++]) {
					case 79:
						if(pos>=token.length) {return -1;}
						switch(token[pos++]) {
							case 84:
								result+=1.0f;
								if(pos>=token.length) {return -1;}
								switch(token[pos++]) {
									case 72:
										return (pos!=token.length) ? -1 : result;
									default:
										return -1;
								}
							case 80:
								return (pos!=token.length) ? -1 : result;
							default:
								return -1;
						}
					default:
						return -1;
				}
			case 80:
				result+=2.0f;
				if(pos>=token.length) {return -1;}
				switch(token[pos++]) {
					case 79:
						if(pos>=token.length) {return -1;}
						switch(token[pos++]) {
							case 80:
								return (pos!=token.length) ? -1 : result;
							default:
								return -1;
						}
					default:
						return -1;
				}
			case 84:
				result+=5.0f;
				if(pos>=token.length) {return -1;}
				switch(token[pos++]) {
					case 79:
						if(pos>=token.length) {return -1;}
						switch(token[pos++]) {
							case 80:
								return (pos!=token.length) ? -1 : result;
							default:
								return -1;
						}
					default:
						return -1;
				}
			case 83:
				result+=3.0f;
				if(pos>=token.length) {return -1;}
				switch(token[pos++]) {
					case 84:
						if(pos>=token.length) {return -1;}
						switch(token[pos++]) {
							case 79:
								result+=1.0f;
								if(pos>=token.length) {return -1;}
								switch(token[pos++]) {
									case 80:
										return (pos!=token.length) ? -1 : result;
									default:
										return -1;
								}
							case 65:
								if(pos>=token.length) {return -1;}
								switch(token[pos++]) {
									case 82:
										return (pos!=token.length) ? -1 : result;
									default:
										return -1;
								}
							default:
								return -1;
						}
					default:
						return -1;
				}
			default:
				return -1;
		}
	}
}
