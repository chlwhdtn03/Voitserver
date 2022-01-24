package com.chlwhdtn.voit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class FileManager {
	
	private int pagenation=5;
	File dir = new File("debate/");
	
	public void saveDebate(Debate debate) {
		File f = new File("debate/"+debate.id+".txt");
		if(!dir.isDirectory())
			dir.mkdirs();
		if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		System.out.println("Saving " + f.getPath());
		
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(f));
			bf.write(new Gson().toJson(debate));
			bf.flush();
			bf.close();
			bf = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Debate loadDebate(String id) {
		File f = new File("debate/"+id+".txt");
		if(!f.exists())
			return null;
		BufferedReader br;
		

		System.out.println("Loading " + f.getPath());
		try {
			br = new BufferedReader(new FileReader(f));
			Debate result = new Gson().fromJson(br.readLine(), Debate.class);
			br.close();
			br=null;
			return result;
			
		} catch (IOException | JsonSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean hasDebate(String id) {
		return new File("debate/"+id+".txt").exists();
	}

	public Debate[] loadDebates(int page) {
		if(size() == 0) 
			return null;
		File[] files = dir.listFiles();
		Arrays.sort(files, Comparator.comparingLong(File::lastModified));
		
		int length = files.length%pagenation;
		Debate[] result = new Debate[length];
		
		for(int i = 0; i < length; i++) {
			result[i] = loadDebate(files[page*pagenation+i].getName().split("\\.")[0]);
		}
		if(result.length == 0)
			return null;
		return result;
	}
	
	public int size() {
		if(dir.isDirectory() == false)
			return 0;
		
		return dir.list().length;
	}
	
}
