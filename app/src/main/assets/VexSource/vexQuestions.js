VF = Vex.Flow;

// calculate correct note

var level
level = android.javaScriptGetLevel();
if (level == '3' | level == '7'){
min = Math.ceil(0);
max = Math.floor(3);
} else if (level == '5' | level == '9'){
min = Math.ceil(0);
max = Math.floor(4);
}

do{
var randomTry = Math.floor(Math.random() * (max - min + 1)) + min;
var same = android.rememberRandom(randomTry)
}
while(same == '1');

var correctNoteNumber = randomTry;
//determine which clef to display

var isTreble
isTreble = android.javaScriptGetClef();
var stringClef;

if (isTreble){
    stringClef = "treble";

} else{

    stringClef = "bass";
}


var div = document.getElementById("question")
var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
renderer.resize(289, 240);

  var ctx = renderer.getContext();
  ctx.scale(3, 3);
var stave = new Vex.Flow.Stave(10, -20, 70);


  //renderer.resize(200, 100);
stave.addClef(stringClef).setContext(ctx).draw();

var noteLetter;
var accidental;

var pitch;
if (level == '5'){
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
} else if (level == '3'){
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
else if (level == '9'){
         switch (correctNoteNumber){
         	case 0: noteLetter = "G";
         	pitch = "/2";
           				break;
         	case 1: noteLetter = "B";
         	pitch = "/2";
           				break;
         	case 2: noteLetter = "D";
         	pitch = "/3";
           				break;
         	case 3:	noteLetter = "F";
         	pitch = "/3";
           				break;
            case 4:	noteLetter = "A";
            pitch = "/3";
                        break;
         	}
        }
    else if (level == '7'){
             switch (correctNoteNumber){
                case 0: noteLetter = "A";
                pitch = "/2";
                            break;
                case 1: noteLetter = "C";
                pitch = "/3";
                            break;
                case 2: noteLetter = "E";
                pitch = "/3";
                            break;
                case 3:	noteLetter = "G";
                pitch = "/3";
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
    new Vex.Flow.StaveNote({clef: stringClef, keys: [noteLetter+pitch], duration: "q", auto_stem: true}),
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


