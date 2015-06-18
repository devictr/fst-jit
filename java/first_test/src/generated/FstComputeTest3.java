package generated;
class FstComputeTest3 {
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
			default:
				return -1;
		}
	}
}
