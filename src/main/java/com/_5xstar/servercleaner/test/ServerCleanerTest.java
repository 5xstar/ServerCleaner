package com._5xstar.servercleaner.test;

import com._5xstar.servercleaner.ServerCleaner;
import com._5xstar.servercleaner.Cleaner;
import com._5xstar.servercleaner.Judger;
import com._5xstar.servercleaner.Preparer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试
 * 庞海文  2024-1-9
 */
public class ServerCleanerTest {
    public static void main(String[] args) {
        demo(args);
    }
    public static void demo(String[] args) {
        Judger judger = null;
        try {
            String html ="<%@ page contentType=\"text/html;charset=GBK\" language=\"java\"%><%@ page import=\"counter.Counter\"%><% Counter.count(request, response, \"2023-07-28-10-00-19_1\", \"强基初中数学&学Python——第208课 数字和数学模块之三：decimal模块（1）——快速入门\", \"208.png\");  %><html xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:dt=\"uuid:C2F41010-65B3-11d1-A29F-00AA00C14882\" xmlns=\"http://www.w3.org/TR/REC-html40\"><head><meta http-equiv=Content-Type  content=\"text/html; charset=gb2312\" ><meta name=ProgId  content=Word.Document ><meta name=Generator  content=\"Microsoft Word 14\" ><meta name=Originator  content=\"Microsoft Word 14\" ><link rel=File-List  href=\"208.files/filelist.xml\" ><!--[if gte mso 9]><xml><o:DocumentProperties><o:Author>e国阳光</o:Author><o:LastAuthor>e国阳光</o:LastAuthor><o:Revision>1</o:Revision><o:Pages>1</o:Pages></o:DocumentProperties><o:CustomDocumentProperties><o:KSOProductBuildVer dt:dt=\"string\" >2052-12.1.0.15120</o:KSOProductBuildVer><o:ICV dt:dt=\"string\" >A50049EEA30D44FE80D78E2D874E8645_11</o:ICV></o:CustomDocumentProperties></xml><![endif]--><!--[if gte mso 9]><xml><o:OfficeDocumentSettings></o:OfficeDocumentSettings></xml><![endif]--><!--[if gte mso 9]><xml><w:WordDocument><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><w:DisplayHorizontalDrawingGridEvery>0</w:DisplayHorizontalDrawingGridEvery><w:DisplayVerticalDrawingGridEvery>2</w:DisplayVerticalDrawingGridEvery><w:DocumentKind>DocumentNotSpecified</w:DocumentKind><w:DrawingGridVerticalSpacing>7.8 磅</w:DrawingGridVerticalSpacing><w:View>Web</w:View><w:Compatibility><w:AdjustLineHeightInTable/><w:DontGrowAutofit/><w:BalanceSingleByteDoubleByteWidth/><w:DoNotExpandShiftReturn/><w:UseFELayout/></w:Compatibility><w:Zoom>0</w:Zoom></w:WordDocument></xml><![endif]--><!--[if gte mso 9]><xml><w:LatentStyles DefLockedState=\"false\"  DefUnhideWhenUsed=\"true\"  DefSemiHidden=\"true\"  DefQFormat=\"false\"  DefPriority=\"99\"  LatentStyleCount=\"260\" >\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  QFormat=\"true\"  Name=\"Normal\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  QFormat=\"true\"  Name=\"heading 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 7\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 8\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"heading 9\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 7\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 8\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index 9\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 7\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 8\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toc 9\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Normal Indent\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"footnote text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"annotation text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"header\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"footer\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"index heading\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  QFormat=\"true\"  Name=\"caption\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"table of figures\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"envelope address\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"envelope return\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"footnote reference\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"annotation reference\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"line number\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"page number\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"endnote reference\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"endnote text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"table of authorities\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"macro\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"toa heading\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Bullet\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Number\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Bullet 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Bullet 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Bullet 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Bullet 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Number 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Number 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Number 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Number 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  QFormat=\"true\"  Name=\"Title\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Closing\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Signature\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  UnhideWhenUsed=\"false\"  Name=\"Default Paragraph Font\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text Indent\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Continue\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Continue 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Continue 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Continue 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"List Continue 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Message Header\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  QFormat=\"true\"  Name=\"Subtitle\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Salutation\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Date\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text First Indent\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text First Indent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Note Heading\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text Indent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Body Text Indent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Block Text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Hyperlink\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"FollowedHyperlink\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  QFormat=\"true\"  Name=\"Strong\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  QFormat=\"true\"  Name=\"Emphasis\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Document Map\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Plain Text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"E-mail Signature\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Normal (Web)\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Acronym\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Address\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Cite\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Code\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Definition\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Keyboard\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Preformatted\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Sample\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Typewriter\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"HTML Variable\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  UnhideWhenUsed=\"false\"  Name=\"Normal Table\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"annotation subject\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"No List\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"1 / a / i\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"1 / 1.1 / 1.1.1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"Article / Section\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Simple 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Simple 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Simple 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Classic 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Classic 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Classic 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Classic 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Colorful 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Colorful 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Colorful 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Columns 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Columns 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Columns 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Columns 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Columns 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 7\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid 8\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 7\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table List 8\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table 3D effects 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table 3D effects 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table 3D effects 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Contemporary\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Elegant\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Professional\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Subtle 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Subtle 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Web 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Web 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Web 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Balloon Text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Grid\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"0\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Table Theme\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"Placeholder Text\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"No Spacing\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"60\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Shading\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"61\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light List\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"62\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Grid\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"63\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"64\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"65\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"66\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"67\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"68\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"69\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"70\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Dark List\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"71\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Shading\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"72\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful List\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"73\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Grid\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"60\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Shading Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"61\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light List Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"62\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Grid Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"63\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 1 Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"64\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 2 Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"65\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 1 Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"List Paragraph\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"Quote\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"99\"  SemiHidden=\"false\"  Name=\"Intense Quote\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"66\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 2 Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"67\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 1 Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"68\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 2 Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"69\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 3 Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"70\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Dark List Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"71\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Shading Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"72\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful List Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"73\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Grid Accent 1\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"60\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Shading Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"61\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light List Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"62\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Grid Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"63\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 1 Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"64\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 2 Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"65\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 1 Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"66\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 2 Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"67\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 1 Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"68\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 2 Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"69\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 3 Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"70\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Dark List Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"71\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Shading Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"72\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful List Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"73\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Grid Accent 2\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"60\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Shading Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"61\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light List Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"62\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Grid Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"63\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 1 Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"64\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 2 Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"65\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 1 Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"66\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 2 Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"67\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 1 Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"68\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 2 Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"69\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 3 Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"70\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Dark List Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"71\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Shading Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"72\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful List Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"73\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Grid Accent 3\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"60\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Shading Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"61\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light List Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"62\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Grid Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"63\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 1 Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"64\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 2 Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"65\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 1 Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"66\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 2 Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"67\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 1 Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"68\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 2 Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"69\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 3 Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"70\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Dark List Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"71\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Shading Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"72\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful List Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"73\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Grid Accent 4\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"60\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Shading Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"61\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light List Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"62\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Grid Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"63\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 1 Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"64\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 2 Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"65\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 1 Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"66\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 2 Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"67\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 1 Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"68\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 2 Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"69\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 3 Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"70\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Dark List Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"71\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Shading Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"72\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful List Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"73\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Grid Accent 5\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"60\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Shading Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"61\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light List Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"62\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Light Grid Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"63\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 1 Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"64\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Shading 2 Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"65\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 1 Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"66\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium List 2 Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"67\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 1 Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"68\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 2 Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"69\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Medium Grid 3 Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"70\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Dark List Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"71\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Shading Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"72\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful List Accent 6\" ></w:LsdException>\n" +
                    "<w:LsdException Locked=\"false\"  Priority=\"73\"  SemiHidden=\"false\"  UnhideWhenUsed=\"false\"  Name=\"Colorful Grid Accent 6\" ></w:LsdException>\n" +
                    "</w:LatentStyles></xml><![endif]--><style>\n" +
                    "@font-face{\n" +
                    "font-family:\"Times New Roman\";\n" +
                    "}\n" +
                    "\n" +
                    "@font-face{\n" +
                    "font-family:\"宋体\";\n" +
                    "}\n" +
                    "\n" +
                    "@font-face{\n" +
                    "font-family:\"Wingdings\";\n" +
                    "}\n" +
                    "\n" +
                    "@font-face{\n" +
                    "font-family:\"Calibri\";\n" +
                    "}\n" +
                    "\n" +
                    "@font-face{\n" +
                    "font-family:\"Microsoft YaHei UI\";\n" +
                    "}\n" +
                    "\n" +
                    "p.MsoNormal{\n" +
                    "mso-style-name:正文;\n" +
                    "mso-style-parent:\"\";\n" +
                    "margin:0pt;\n" +
                    "margin-bottom:.0001pt;\n" +
                    "mso-pagination:none;\n" +
                    "text-align:justify;\n" +
                    "text-justify:inter-ideograph;\n" +
                    "font-family:Calibri;\n" +
                    "mso-fareast-font-family:宋体;\n" +
                    "mso-bidi-font-family:'Times New Roman';\n" +
                    "font-size:10.5000pt;\n" +
                    "mso-font-kerning:1.0000pt;\n" +
                    "}\n" +
                    "\n" +
                    "span.10{\n" +
                    "font-family:'Times New Roman';\n" +
                    "}\n" +
                    "\n" +
                    "span.15{\n" +
                    "font-family:'Times New Roman';\n" +
                    "mso-ansi-font-weight:bold;\n" +
                    "}\n" +
                    "\n" +
                    "p.p{\n" +
                    "mso-style-name:\"普通\\(网站\\)\";\n" +
                    "margin-top:5.0000pt;\n" +
                    "margin-right:0.0000pt;\n" +
                    "margin-bottom:5.0000pt;\n" +
                    "margin-left:0.0000pt;\n" +
                    "mso-margin-top-alt:auto;\n" +
                    "mso-margin-bottom-alt:auto;\n" +
                    "mso-pagination:none;\n" +
                    "text-align:left;\n" +
                    "font-family:Calibri;\n" +
                    "mso-fareast-font-family:宋体;\n" +
                    "mso-bidi-font-family:'Times New Roman';\n" +
                    "font-size:12.0000pt;\n" +
                    "}\n" +
                    "\n" +
                    "span.msoIns{\n" +
                    "mso-style-type:export-only;\n" +
                    "mso-style-name:\"\";\n" +
                    "text-decoration:underline;\n" +
                    "text-underline:single;\n" +
                    "color:blue;\n" +
                    "}\n" +
                    "\n" +
                    "span.msoDel{\n" +
                    "mso-style-type:export-only;\n" +
                    "mso-style-name:\"\";\n" +
                    "text-decoration:line-through;\n" +
                    "color:red;\n" +
                    "}\n" +
                    "\n" +
                    "table.MsoNormalTable{\n" +
                    "mso-style-name:普通表格;\n" +
                    "mso-style-parent:\"\";\n" +
                    "mso-style-noshow:yes;\n" +
                    "mso-tstyle-rowband-size:0;\n" +
                    "mso-tstyle-colband-size:0;\n" +
                    "mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt;\n" +
                    "mso-para-margin:0pt;\n" +
                    "mso-para-margin-bottom:.0001pt;\n" +
                    "mso-pagination:widow-orphan;\n" +
                    "font-family:'Times New Roman';\n" +
                    "font-size:10.0000pt;\n" +
                    "mso-ansi-language:#0400;\n" +
                    "mso-fareast-language:#0400;\n" +
                    "mso-bidi-language:#0400;\n" +
                    "}\n" +
                    "@page{mso-page-border-surround-header:no;\n" +
                    "\tmso-page-border-surround-footer:no;}@page Section0{\n" +
                    "margin-top:72.0000pt;\n" +
                    "margin-bottom:72.0000pt;\n" +
                    "margin-left:90.0000pt;\n" +
                    "margin-right:90.0000pt;\n" +
                    "size:595.3000pt 841.9000pt;\n" +
                    "layout-grid:15.6000pt;\n" +
                    "mso-header-margin:42.5500pt;\n" +
                    "mso-footer-margin:49.6000pt;\n" +
                    "}\n" +
                    "div.Section0{page:Section0;}</style>\n" +
                    "<% if(request.getParameter(\"self\")==null){\n" +
                    "out.write(\"<script language=\\\"JavaScript\\\">\");\n" +
                    "out.write(\"self.location='https://mp.weixin.qq.com/s?__biz=MzAxMTkzMTE3NQ==&mid=2247496470&idx=1&sn=ac16e4061d2c43db2e0a41b1947fb35f&chksm=9bbb285eaccca148626725136d138466ae10774c13919893f413a2775fe79d5b15b0af7c6f4b&token=547640891&lang=zh_CN#rd';\");\n" +
                    "out.write(\"</script>\");} %>\n" +
                    "<title>强基初中数学&学Python——第208课 数字和数学模块之三：decimal模块（1）——快速入门</title><!-- mainImg>208.png</mainImg --></head><body style=\"tab-interval:21pt;text-justify-trim:punctuation;\" ><p align=\"center\"><h3>强基初中数学&学Python——第208课 数字和数学模块之三：decimal模块（1）——快速入门</h3></p><!--StartFragment--><div class=\"Section0\"  style=\"layout-grid:15.6000pt;\" ><p class=p  style=\"margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:14.4000pt;\n" +
                    "margin-left:0.0000pt;text-indent:0.0000pt;padding:0pt 0pt 0pt 0pt ;\n" +
                    "mso-pagination:widow-orphan;text-align:left;background:rgb(255,255,255);\" ><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;text-transform:none;font-style:normal;\n" +
                    "font-size:10.0000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);\n" +
                    "mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><b style=\"mso-bidi-font-weight:normal\" ><span class=\"15\"  style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;mso-ansi-font-weight:bold;text-transform:none;\n" +
                    "font-style:normal;font-size:14.0000pt;background:rgb(255,255,255);\n" +
                    "mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >decimal模块介绍</font></span></b><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;text-transform:none;font-style:normal;\n" +
                    "font-size:10.0000pt;mso-font-kerning:0.0000pt;\" ><o:p></o:p></span></p><p class=MsoNormal ><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';letter-spacing:0.3500pt;\n" +
                    "font-size:12.0000pt;mso-font-kerning:1.0000pt;\" ><font face=\"Microsoft YaHei UI\" >　　</font><font face=\"Microsoft YaHei UI\" >decimal模块提供快速十进制精确小数的运算。与float数据类型相比，它具有以下几个优点：</font></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　（</font><font face=\"Microsoft YaHei UI\" >1）与我们心中的十进制小数概念相同；　　（2）数字是完全精确的；　　（3）算术运算（加减乘除）操作过程中的数也是精确的；　　（4）包含有效位的概念，不自动去除小数中后面的0；　　（5）可更改精度（默认是28位），与问题所需的精度一致；　　（6）公开所有必要的接口，这样程序员可以通过舍入和信号处理达到所需的要求，这包括通过异常处理避免不精确的运算，从而得到精确的结果；　　（7）提供无偏差无舍入的十进制算术运算（也称为定点数算术）和有舍入的浮点数算术。　　</font></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';letter-spacing:0.3500pt;\n" +
                    "font-size:12.0000pt;mso-font-kerning:1.0000pt;\" ><font face=\"Microsoft YaHei UI\" >该模块以三个概念为中心：</font><font face=\"Microsoft YaHei UI\" >Decimal精确小数对象，算术上下文对象和信号。</font></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >decimal数值是不可变对象（可作为字典的键）。它由符号，系数和指数位组成。为了保持有效位，系数位不会截去末尾零。decimal数值也包括特殊值例如 Infinity ，-Infinity 和 NaN 。还区分 -0 和 +0 。</span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >算术上下文是指定精度、舍入规则、指数限制等。</font>&nbsp;</span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >信号是在计算过程中出现的一组异常状况。根据应用程序的需要，信号可能会被忽略、被视为信息或异常。</font>&nbsp;</span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';letter-spacing:0.3500pt;\n" +
                    "font-size:12.0000pt;mso-font-kerning:1.0000pt;\" ><font face=\"Microsoft YaHei UI\" >对于每个信号，都关联一个标志和一个陷阱。遇到信号时，其标志设置为</font><font face=\"Microsoft YaHei UI\" >1 ，然后，如果陷阱设置为1 ，则引发异常。标志像告事贴，因此用户需要在监控计算之前重置它们。</font></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><b style=\"mso-bidi-font-weight:normal\" ><span class=\"15\"  style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';letter-spacing:0.3500pt;\n" +
                    "mso-ansi-font-weight:bold;font-size:14.0000pt;\" ><font face=\"Microsoft YaHei UI\" >快速入门教程</font></span></b><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　通常使用</font><font face=\"Microsoft YaHei UI\" >decimal的方式是先导入该模块，可以通过getcontext()查看当前上下文，如有必要时为精度、舍入方式或陷阱设置为新值：</font></span><img width=\"977\"  height=\"131\"  src=\"208.files/208683.png\" ><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >可以通过整数、字符串、浮点数或元组构造</font>Decimal对象。&nbsp;通过整数或浮点数构造时，会把该整数或浮点数的值转换精确。Decimal对象（精确数）包括特殊值例如 NaN 表示“非数字”，正的和负的Infinity和-0。</span><img width=\"713\"  height=\"380\"  src=\"208.files/208795.png\" ><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >如果</font>FloatOperation陷阱被设置为抛出状态（True），构造函数中的参数是浮点数、精确小数（Decimal）和浮点数混合排序比较（&gt;,&lt;,&gt;=,&lt;=）会引发异常。</span><img width=\"636\"  height=\"343\"  src=\"208.files/208884.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >Decimal的精确性仅由输入的位数决定，而上下文中的精度和舍入设置仅在算术运算期间发挥作用。</span><img width=\"595\"  height=\"230\"  src=\"208.files/208935.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >构造一个</font>Decimal</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >时，即使超出了</font>Emax的限制，也不会抛出异常，但运行结果超出</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >Emax的限制会抛出Overflow异常；但</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >如果超出了运行层（</font>C语言）版本的内部限制，也会引发InvalidOperation。</span><img width=\"981\"  height=\"325\"  src=\"208.files/2081044.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >Decimal能够像浮点数一样使用，数字的操作符和内置函数一样可以用于</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >Decimal</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >。下面是一个分币制数列表的有关运算：</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><img width=\"980\"  height=\"548\"  src=\"208.files/2081109.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >注：</font>map(Decimal, strList)使strList的每一项都进行Decimal()运算，转化为一个序列，再通过list()转为列表。</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >Decimal内部也有</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >一些</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >数学方法可以使用：</font></span><img width=\"492\"  height=\"217\"  src=\"208.files/2081207.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font><font face=\"Microsoft YaHei UI\" >Decimal的</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >quantize()方法可以不改变上下文的精度，将数字舍入为小数点后固定位数。此方法对于将结果舍入到固定的位置的货币应用程序非常有用：</span><img width=\"702\"  height=\"117\"  src=\"208.files/2081286.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　从</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >上面所述中得到，</font>getcontext()函数访问当前上下文并允许更改设置。&nbsp;这种方法满足大多数应用程序的需求。对于更加特殊的计算，就需要使用Context()构造函数创建合适的上下文对象，&nbsp;并使用setcontext()函数设置上下文。默认情况下，decimal模块提供了两个现成的上下文BasicContext和 ExtendedContext对象。&nbsp;前者对调试特别有用，因为许多陷阱已被启用：</span><img width=\"978\"  height=\"445\"  src=\"208.files/2081489.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >上下文还具有用于监视计算期间遇到的异常情况的信号标志。标志不会自动清除，保持直到显式清除，因此最好通过使用</font>clear_flags()方法清除每组受监控计算之前的标志。</span><img width=\"977\"  height=\"261\"  src=\"208.files/2081576.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >flags条目显示对&#960;的有理逼近被舍入（超出上下文精度的数字被抛弃），所以结果是不精确的（一些丢弃的数字不为零）。</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >使用上下文对象的</font>traps字段（字典）设置单个陷阱：</span><img width=\"607\"  height=\"197\"  src=\"208.files/2081664.png\" ><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><br></span><span style=\"mso-spacerun:'yes';font-family:'Microsoft YaHei UI';color:rgb(34,34,34);\n" +
                    "letter-spacing:0.3500pt;font-size:12.0000pt;mso-font-kerning:1.0000pt;\n" +
                    "background:rgb(255,255,255);mso-shading:rgb(255,255,255);\" ><font face=\"Microsoft YaHei UI\" >　　</font></span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >大多数程序只在程序开始时调整当前上下文一次。而且，在许多应用程序中，数据被一次性转换为精确小数（</font>Decimal），就算在循环中也一样。创建了上下文和精确小数后，大部分</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" >Python</span><span style=\"mso-spacerun:'yes';font-family:宋体;font-size:12.0000pt;\n" +
                    "mso-font-kerning:1.0000pt;\" ><font face=\"宋体\" >程序操作精确小数与其他数字类型就没有什么不同了。</font></span><span style=\"mso-spacerun:'yes';font-family:Calibri;mso-fareast-font-family:宋体;\n" +
                    "mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;\" ><o:p></o:p></span></p></div><!--EndFragment--></body></html>\n";
            ServerCleaner sc = ServerCleaner.serverCleaner;
            //Preparer preparer = sc.getPreparerBySerFile(new File("D:\\5xstar\\ServerCleaner\\test.obj"),new File("D:\\5xstar\\ServerCleaner\\test.dat"));
            final List<String> excludeRegexes = new ArrayList<>();
            excludeRegexes.add("(?:^|[^0-9])[0-9]{2,4}[a-z]*?\\.png$");  //不操作主图
            final Set<List<String>> includeDirNames = new HashSet<>();
            List<String> includeDirName = new ArrayList<>();
            includeDirName.add("jn");
            includeDirName.add("test2");  //操作jn/test2/,注意要把父级目录逐级添加
            includeDirNames.add(includeDirName);
            includeDirName = new ArrayList<>();
            includeDirName.add("jn");
            includeDirName.add("xsd");
            includeDirNames.add(includeDirName);
            Preparer preparer = sc.getPreparer(includeDirNames,excludeRegexes);
            preparer.addSeqsFromHtml(html,"middle/mc/Python/6/");
            preparer.prepare(new File("D:\\5xstar\\work\\jn"));
            File data = preparer.finished();
            preparer.close();
            File[] datas = preparer.getSerFiles();
            if(datas!=null && datas.length==2){  //备份Preparer对象和文件数据
                FileUtils.copyFile(datas[0], new File("test.obj"));
                FileUtils.copyFile(datas[1], new File("test.dat"));
            }
            judger = sc.getJudger(preparer, data);
            Cleaner cleaner = sc.getCleaner(preparer,judger);
            cleaner.cleanDir( new File("D:\\5xstar\\work\\jn"));
            judger.close();
            judger=null;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(judger!=null)try{judger.close();}catch (Exception e){}
        }
    }

}
