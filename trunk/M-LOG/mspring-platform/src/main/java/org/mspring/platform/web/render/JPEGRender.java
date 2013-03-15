/*
 * Created on 2012-7-6
 *
 * JPEGRenderer.java
 *
 * Copyright (C) 2012 by Cemso Software & Technology Services (Shanghai) Limited.
 * All rights reserved. Cemso Software & Technology Services (Shanghai) Limited 
 * claims copyright in this computer program as an unpublished work. Claim of copyright 
 * does not imply waiver of other rights.
 *
 * NOTICE OF PROPRIETARY RIGHTS
 *
 * This program is a confidential trade secret and the property of 
 * Cemso Software & Technology Services (Shanghai) Limited. Use, examination, 
 * reproduction, disassembly, decompiling, transfer and/or disclosure to others of 
 * all or any part of this software program are strictly prohibited except by express 
 * written agreement with Cemso Software & Technology Services (Shanghai) Limited.
 */
/*
 * ---------------------------------------------------------------------------------
 * Modification History
 * Date               Author                     Version     Description
 * 2012-7-6       Administrator                    1.0         New
 * ---------------------------------------------------------------------------------
 */
/**
 * 
 */
package org.mspring.platform.web.render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @ClassName: JPEGRenderer
 * @Description: TODO
 * @author Gao Youbo
 * @date 2012-7-6 下午03:00:55
 * @version V1.0
 */
public class JPEGRender extends AbstractResponseRender {

    private static final Logger log = Logger.getLogger(JPEGRender.class);

    private BufferedImage buffImg;

    public void setBuffImg(BufferedImage buffImg) {
        this.buffImg = buffImg;
    }
    
    /**
     * 
     */
    public JPEGRender(BufferedImage buffImg) {
        // TODO Auto-generated constructor stub
        this.buffImg = buffImg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.servlet.renderer.AbstractResponseRenderer#render
     * (javax.servlet.http.HttpServletResponse)
     */
    public void render(HttpServletResponse response) {
        // TODO Auto-generated method stub
        if (this.buffImg == null) {
            return;
        }

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("image/jpeg");
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(buffImg, "JPEG", out);
        }
        catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
