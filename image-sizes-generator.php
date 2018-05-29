<?php

$path = "./app/src/main/res/drawable-nodpi";

if ($handle = opendir($path)) {

    echo "public class DrawableDimensionGenerated {\n";
    echo "public static int[] getDimensionForImageReosurce(int image) {\n";
	echo "swith(image) {\n";

    while (false !== ($file = readdir($handle))) {
        if ('.' === $file) continue;
        if ('..' === $file) continue;

         list($width, $height, $type, $attr) = getimagesize( $path . "/". $file);

$fileName =  explode(".", $file)[0];//substr($file, 0 , strlen($file) - 3);

		echo "case R.drawable." . $fileName . ": return new int[]{ " . $width . ", " . $height . "};\n";
    }

	echo "default: return new int[]{0,0};\n";
	echo "}\n";
	echo "}\n";
    closedir($handle);
}





?>
