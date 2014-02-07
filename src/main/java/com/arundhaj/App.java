package com.arundhaj;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Tamil Songs crawler" );
        
        TamilMp3HubCrawl tamilMp3HubCrawl = new TamilMp3HubCrawl();
//        tamilMp3HubCrawl.movie_page("2006/Pokkiri");
//        tamilMp3HubCrawl.year_page("2005");
        tamilMp3HubCrawl.crawl();
    }
}
