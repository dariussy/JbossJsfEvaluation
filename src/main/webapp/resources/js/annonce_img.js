
$image = $('<img>');

$listImages = $("#gallery img");

var srcImage=$($listImages[0]).attr('src');

console.log(srcImage);

$image.attr('src',srcImage);

$("#annonceImg").append($image);

$("#gallery a").click(function(event){
	event.preventDefault();

	var srcimagecclick=$(this).children('img').attr('src');

	$("#annonceImg img").attr('src',srcimagecclick);

});