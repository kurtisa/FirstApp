VF = Vex.Flow;
min = Math.ceil(0);
max = Math.floor(11);
var correctNoteNumber = Math.floor(Math.random() * (max - min + 1)) + min;



var canvas = $("#container")[0];
  var renderer = new Vex.Flow.Renderer(canvas,
    Vex.Flow.Renderer.Backends.CANVAS);

  var ctx = renderer.getContext();
  var stave = new Vex.Flow.Stave(45, 0, 75);
  stave.addClef("treble").setContext(ctx).draw();

var noteLetter;
var accidental;
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

  var notes = [
    // A quarter-note C.
    new Vex.Flow.StaveNote({ keys: [noteLetter+"/4"], duration: "q" }),


  ];


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

android.updateButtonText(correctNoteNumber);


