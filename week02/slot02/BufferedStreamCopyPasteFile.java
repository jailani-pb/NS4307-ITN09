package week02.slot02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class BufferedStreamCopyPasteFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please type source file to copy");
		String source = scanner.nextLine();
		File sourceFile = new File(source);
		while(!sourceFile.exists()) {
			System.out.println("Source file not exists");
			System.out.println("Please type source file to copy");
			source = scanner.nextLine();
			sourceFile = new File(source);
		}
		
		System.out.println("Please type target file to paste");
		String target = scanner.nextLine();
		File targetFile = new File(target);
		while(targetFile.exists()) {
			System.out.println("Target file exists.");
			System.out.println("Will not overwrite file.");
			System.out.println("Please type target file to paste");
			target = scanner.nextLine();
			targetFile = new File(target);
		}
		
		try(
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream(sourceFile));
			BufferedOutputStream output = new BufferedOutputStream(
					new FileOutputStream(targetFile));
				) {
			int fileContent = 0;
			int numberOfBytesCopied = 0;
			while((fileContent = input.read()) != -1) {
				output.write((byte) fileContent);
				numberOfBytesCopied++;
			}
			System.out.println("Copying file complete.");
			System.out.println("No of Bytes copied: ");
			System.out.println(numberOfBytesCopied);
		}
		
		scanner.close();
	}
	
}
