package com.hf.lesson18;

public class MakeDirectories {
	private static void usage() {
		System.err.println(
		"Usage:MakeDirectories path1 ...\n" +
		"Creates each path\n" +
		"Usage:MaakeDirectories -d path1 ...\n"+
		"Deletes each path\n"+
		"Usage:MakeDirectories -r path1 path2\n"+
		"Renames from path1 to path2"
		);
		System.exit(1);
	}
	
}
