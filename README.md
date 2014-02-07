songs_crawl
===========

crawler i've written in Java. 

Fetchs the list of all movies and songs from tamilmp3hub website.

Outputs in hierarchical XML. One XML file for each decade and each XML in the following format.

	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
	<Results>
		<Year id="2010">
			<Movie id="Aadukalam">
				<Song id="A Love Blossoms"/>
				<Song id="Ayyayo"/>
				<Song id="En Vennilave"/>
				<Song id="Otha Sollaala"/>
				<Song id="Porkkalam Tamil-Rap"/>
				<Song id="Warriors - English Rap"/>
				<Song id="Yathe Yathe"/>
			</Movie>
		</Year>
	</Results>

