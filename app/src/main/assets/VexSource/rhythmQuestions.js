VF = Vex.Flow;

// calculate correct note

var level
level = android.javaScriptGetRhythmLevel();
if (level == '8'){
min = Math.ceil(0);
max = Math.floor(3);
} else if (level == '9'){
min = Math.ceil(4);
max = Math.floor(7);
}
do{
var randomTry = Math.floor(Math.random() * (max - min + 1)) + min;
var same = android.rememberRandom(randomTry)
}
while(same == '1');

var correctRhythmNumber = randomTry;

var correctRhythmNumber
var beats;
var beatValue;



var div = document.getElementById("question");
  var renderer = new Vex.Flow.Renderer(div,
    Vex.Flow.Renderer.Backends.SVG);
renderer.resize(289, 240);

  var ctx = renderer.getContext();
  ctx.scale(3, 3);

var stave = new Vex.Flow.Stave(10, -20, 70);

    //renderer.resize(289, 333);
    stave.setContext(ctx).draw();

if (level == '8'){
  switch (correctRhythmNumber){
  	case 0: rhythmLetter = "w";
                beats=4;
                beatValue = 4;
    				break;
  	case 1: rhythmLetter = "h";
                beats=2;
                beatValue = 4;
    				break;
  	case 2: rhythmLetter = "q";
                beats=1;
                beatValue = 4;
    				break;
  	case 3:	rhythmLetter = "8";
                beats=1;
                beatValue = 8;
                    break;

    }
}else if (level == '9'){
         switch (correctRhythmNumber){
         	case 0: rhythmLetter = "w";
                    beats=4;
                    beatValue = 4;
           				break;
         	case 1: rhythmLetter = "h";
                    beats=2;
                    beatValue = 4;
           				break;
         	case 2: rhythmLetter = "q";
                     beats=1;
                     beatValue = 4;
           				break;
         	case 3:	rhythmLetter = "8";
                     beats=1;
                     beatValue = 8;
           				break;
            case 4: rhythmLetter = "wr";
                     beats=4;
                     beatValue = 4;
                        break;
            case 5: rhythmLetter = "hr";
                     beats=2;
                     beatValue = 4;
                        break;
            case 6: rhythmLetter = "qr";
                     beats=1;
                     beatValue = 4;
                        break;
            case 7:	rhythmLetter = "8r";
                     beats=1;
                     beatValue = 8;
                        break;

         	}
       }

 android.updateJavascriptRhythm(correctRhythmNumber);

//determine time signature



    var notes;
    // A quarter-note C.

 notes = [
     new Vex.Flow.StaveNote({clef: "treble", keys: ["G/4"], duration: rhythmLetter }),
     ];


  // Create a voice in 1/4
  var voice = new Vex.Flow.Voice({
     num_beats: beats,
     beat_value: beatValue,
    resolution: Vex.Flow.RESOLUTION
  });

  // Add notes to voice
  voice.addTickables(notes);

  // Format and justify the notes to 500 pixels
  var formatter = new Vex.Flow.Formatter().
    joinVoices([voice]).format([voice], 500);

  // Render voice
//  if (rhythmLetter.equals("8r") || rhythmLetter.equal("wr")){
//      setContext(ctx).draw();
//   }
//   else{
//ctx.scale(2, 2);
  voice.draw(ctx, stave);
//  }