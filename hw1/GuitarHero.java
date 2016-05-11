import synthesizer.GuitarString;

public class GuitarHero {
	private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

	public static void main(String[] args) {
		GuitarString[] stringAll = new GuitarString[37];
		
		for(int i = 0; i < 37; i++) {
			double concert = 440 * Math.pow(2, ((i - 24) / 12));
			stringAll[i] = new GuitarString(concert);
		} 

		while(true) {

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                stringAll[keyboard.indexOf(key)].pluck();
            }

            double sample = 0.0;
			for(int i = 0; i < 37; i++) {
				sample += stringAll[i].sample();
			}

			StdAudio.play(sample);

			for(int i = 0; i < 37; i++) {
				stringAll[i].tic();
			}

		}


	}
}