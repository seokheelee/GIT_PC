package com.cj.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

public class SpecializedScreenRecorder extends ScreenRecorder {

	public SpecializedScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
			Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
					throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.name = name;
	}

	protected File createMovieFile(Format fileFormat) throws IOException {
		if (!movieFolder.exists())
			movieFolder.mkdirs();
		else if (!movieFolder.isDirectory())
			throw new IOException(
					(new StringBuilder("\"")).append(movieFolder).append("\" is not a directory.").toString());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		return new File(movieFolder,
				(new StringBuilder(String.valueOf(name))).append("-").append(dateFormat.format(new Date())).append(".")
						.append(Registry.getInstance().getExtension(fileFormat)).toString());
	}

	private String name;
}
