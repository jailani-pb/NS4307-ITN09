package week05.slot01.multipleclientserver;

import java.util.Random;

public class LearningConcurrency {

	public static void main(String[] args) {
		for(int i = 0; i < 3; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for(int j = 0; j < 100; j++) {
						Random r = new Random();
						System.out.println(
							r.nextInt(100));
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}).start();
		}
	}
	
}
