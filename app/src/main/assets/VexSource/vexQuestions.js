VF = Vex.Flow;

// calculate correct note

var level
level = android.javaScriptGetLevel();
if (level == '1'){
min = Math.ceil(0);
max = Math.floor(4);
} else if (level == '2'){
min = Math.ceil(0);
max = Math.floor(3);
} else{
min = Math.ceil(0);
max = Math.floor(11);
}
var correctNoteNumber = Math.floor(Math.random() * (max - min + 1)) + min;


//determine which clef to display

var isTreble
isTreble = android.javaScriptGetClef();
var stringClef;

if (isTreble){
    stringClef = "treble";

} else{

    stringClef = "bass";
}


var canvas = $("#container")[0];
  var renderer = new Vex.Flow.Renderer(canvas,
    Vex.Flow.Renderer.Backends.CANVAS);

  var ctx = renderer.getContext();
  var stave = new Vex.Flow.Stave(60, 0, 75);
  //renderer.resize(200, 100);
  stave.addClef(stringClef).setContext(ctx).draw();

var noteLetter;
var accidental;

if (level == '3'){
  switch (correctNoteNumber){
  	case 0: noteLetter = "A";
    				break;
  	case 1: noteLetter = "Bb";
    				break;
  	case 2: noteLetter = "B";
    				break;
  	case 3:	noteLetter = "C";
    				break;
  	case 4:	noteLetter = "C#";
        				break;
  	case 5: noteLetter = "D";
        				break;
  	case 6: noteLetter = "Eb";
        				break;
  	case 7: noteLetter = "E";
        				break;
  	case 8:	noteLetter = "F";
        				break;
  	case 9: noteLetter = "F";
        				break;
  	case 10: noteLetter = "G";
        				break;
  	case 11: noteLetter = "Ab";
        				break;
  	}
}
var pitch;
if (level == '1'){
  switch (correctNoteNumber){
  	case 0: noteLetter = "E";
  	pitch = "/4";
    				break;
  	case 1: noteLetter = "G";
  	pitch = "/4";
    				break;
  	case 2: noteLetter = "B";
  	pitch = "/4";
    				break;
  	case 3:	noteLetter = "D";
  	pitch = "/5";
    				break;
  	case 4:	noteLetter = "F";
  	pitch = "/5";
        				break;
  	}
} else if (level == '2'){
         switch (correctNoteNumber){
         	case 0: noteLetter = "F";
         	pitch = "/4";
           				break;
         	case 1: noteLetter = "A";
         	pitch = "/4";
           				break;
         	case 2: noteLetter = "C";
         	pitch = "/5";
           				break;
         	case 3:	noteLetter = "E";
         	pitch = "/5";
           				break;
         	}
       }

    android.updateJavascriptNote(noteLetter);

    var notes;
    // A quarter-note C.

    if (noteLetter.length == 2){
    notes = [
    new Vex.Flow.StaveNote({clef: stringClef, keys: [noteLetter+pitch], duration: "q" }).
    addAccidental(0, new VF.Accidental(noteLetter.charAt(1))),
    ];
    } else {
        notes = [
    new Vex.Flow.StaveNote({clef: stringClef, keys: [noteLetter+pitch], duration: "q" }),
    ];
    }



  // Create a voice in 1/4
  var voice = new Vex.Flow.Voice({
    num_beats: 1,
    beat_value: 4,
    resolution: Vex.Flow.RESOLUTION
  });

  // Add notes to voice
  voice.addTickables(notes);

  // Format and justify the notes to 500 pixels
  var formatter = new Vex.Flow.Formatter().
    joinVoices([voice]).format([voice], 500);

  // Render voice
  voice.draw(ctx, stave);


