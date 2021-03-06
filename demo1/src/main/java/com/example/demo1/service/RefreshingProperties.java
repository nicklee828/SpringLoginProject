package com.example.demo1.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RefreshingProperties extends Properties {

	  private final File file;
	
	  public RefreshingProperties (File file) throws IOException { 
	    this.file = file;
	    refresh ();
	  }
	
	  private void refresh () throws IOException {
	    load (new FileInputStream (file));
	  }
	
	  @Override
	  public String getProperty (String name) {
	    try { refresh (); }
	    catch (IOException e) {}
	    return super.getProperty(name);
	  }
}