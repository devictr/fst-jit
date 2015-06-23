package generated;
public class GenJavaSwitch {
	public static float compute(int[] token) {
		int pos=0;
		float result=0f;
		if(pos>=token.length) {return -1;}
		switch(token[pos++]) {
			case 65:
				if(pos==token.length) {return result;}
				if(pos>=token.length) {return -1;}
				switch(token[pos++]) {
					case 97:
						if(pos>=token.length) {return -1;}
						switch(token[pos++]) {
							case 110:
								if(pos>=token.length) {return -1;}
								switch(token[pos++]) {
									case 105:
										if(pos==token.length) {return result;}
										return (pos!=token.length) ? -1 : result;
									default:
										return -1;
								}
							case 114:
								if(pos>=token.length) {return -1;}
								switch(token[pos++]) {
									case 111:
										if(pos>=token.length) {return -1;}
										switch(token[pos++]) {
											case 110:
												if(pos==token.length) {return result;}
												if(pos>=token.length) {return -1;}
												switch(token[pos++]) {
													case 105:
														if(pos>=token.length) {return -1;}
														switch(token[pos++]) {
															case 99:
																if(pos==token.length) {return result;}
																if(pos>=token.length) {return -1;}
																switch(token[pos++]) {
																	case 97:
																		result+=5f;
																		if(pos>=token.length) {return -1;}
																		switch(token[pos++]) {
																			case 108:
																				if(pos==token.length) {return result;}
																				return (pos!=token.length) ? -1 : result;
																			default:
																				return -1;
																		}
																	default:
																		return -1;
																}
															case 116:
																if(pos>=token.length) {return -1;}
																switch(token[pos++]) {
																	case 101:
																		if(pos==token.length) {return result;}
																		return (pos!=token.length) ? -1 : result;
																	case 105:
																		result+=7f;
																		if(pos>=token.length) {return -1;}
																		switch(token[pos++]) {
																			case 99:
																				if(pos==token.length) {return result;}
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
											default:
												return -1;
										}
									case 117:
										result+=8f;
										if(pos==token.length) {return result;}
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
