VF = Vex.Flow;

// calculate correct note

var level
level = android.javaScriptGetTouchLevel();

if (level == '6'){
min = Math.ceil(0);
max = Math.floor(4);
} else if (level == '5'){
min = Math.ceil(0);
max = Math.floor(3);
}
do{
var randomTry = Math.floor(Math.random() * (max - min + 1)) + min;
var same = android.rememberRandom(randomTry)
}
while(same == '1');

var correctNoteNumber = randomTry;

 //determine which clef to display

 var isTreble
 isTreble = android.javaScriptGetTouchClef();

 var stringClef;

 if (isTreble){
     stringClef = "treble";

 } else{
     stringClef = "bass";
 }

var div = document.getElementById("question")
var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
renderer.resize(289, 333);
var ctx = renderer.getContext();
ctx.scale(4, 4); // scale X and Y
var stave = new Vex.Flow.Stave(0, -20, 70);
stave.addClef(stringClef).setContext(ctx).draw();


var noteLetter;
var accidental;



var noteLetter;
var accidental;


var pitch;
if (level == '6'){
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
} else if (level == '5'){
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
  // var note;
var tickContext = new Vex.Flow.TickContext();
   var note =  new Vex.Flow.StaveNote({clef: stringClef, keys: [noteLetter + pitch], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);

    if (level == '5'){
   var noteF =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["F/4"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
   var noteA =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["A/4"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
   var noteC =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["C/5"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
   var noteE =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["E/5"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
   tickContext.addTickable(noteF)
   tickContext.addTickable(noteA)
   tickContext.addTickable(noteC)
   tickContext.addTickable(noteE)
   } else if (level == '6'){
    var FnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["F/5"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
    var DnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["D/5"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
    var BnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["B/4"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
    var GnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["G/4"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
    var EnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["E/4"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
    tickContext.addTickable(FnoteLines)
    tickContext.addTickable(DnoteLines)
    tickContext.addTickable(BnoteLines)
    tickContext.addTickable(GnoteLines)
    tickContext.addTickable(EnoteLines)
    }
    // Create a voice in 1/4


  /*  var voice = new Vex.Flow.Voice({
             num_beats: 1,
             beat_value: 4,


 // tickContext.preFormat().setX(0)

   /* var formatter = new Vex.Flow.Formatter().
    joinVoices([voice]).format([voice], 500);*/

      div.addEventListener('touchstart', function(e){
      var wrong = 0;
      var div = document.getElementById("question")
          var clientX;
          var clientY;
          var notesY;
          var notesX;
                var touchobj = e.changedTouches[0] // reference first touch point (ie: first finger)
                clientX = parseInt(touchobj.clientX) // get x position of touch point relative to left edge of browser
                clientY = parseInt(touchobj.clientY)
                e.preventDefault()

                if (level == '5'){
                notesX = (noteF.getAbsoluteX() * 4 + 10);
                notesY = (noteF.getYs() * 4);
                android.javaScriptGetTouch(clientX, clientY, notesY, notesX)

                 if ((clientY < (noteF.getYs() * 4 + 13))  && (clientY > (noteF.getYs() * 4 - 13))){
                    if (clientX > notesX){
                    tickContext.preFormat().setX(clientX/4 - notesX/4)
                    }
                  if (noteLetter == "F"){
                    noteF.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                    const group = ctx.openGroup(); // create an SVG group element
                    noteF.draw();
                    ctx.closeGroup();
                    setCorrect(group)
                  } else{
                  const group = ctx.openGroup(); // create an SVG group element
                  noteF.draw();
                  ctx.closeGroup();
                    setWrong(group)
                  }
                 }

                    //A note space!
                  notesX = (noteA.getAbsoluteX() * 4 + 10);
                  notesY = (noteA.getYs() * 4);
                  android.javaScriptGetTouch(clientX, clientY, notesY, notesX)
                  // check if note is note A
                 notesX = (noteA.getAbsoluteX() * 4 + 10);
                 notesY = (noteA.getYs() * 4);

                 if ((clientY < (noteA.getYs() * 4 + 13))  && (clientY > (noteA.getYs() * 4 - 13))){
                      if (clientX > notesX){
                      tickContext.preFormat().setX(clientX/4 - notesX/4)
                      }

                    if (noteLetter == "A"){
                        noteA.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                        const group = ctx.openGroup(); // create an SVG group element
                        noteA.draw();
                        ctx.closeGroup();
                        setCorrect(group)

                    } else{
                    const group = ctx.openGroup(); // create an SVG group element
                    noteA.draw();
                    ctx.closeGroup();
                      setWrong(group)
                    }
                 }

                 notesX = (noteC.getAbsoluteX() * 4 + 10);
                 notesY = (noteC.getYs() * 4);
                 if ((clientY < (noteC.getYs() * 4 + 13))  && (clientY > (noteC.getYs() * 4 - 13))){
                      if (clientX > notesX){
                      tickContext.preFormat().setX(clientX/4 - notesX/4)
                      }
                    if (noteLetter == "C"){
                        noteC.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                        const group = ctx.openGroup(); // create an SVG group element
                        noteC.draw();
                        ctx.closeGroup();
                        setCorrect(group)
                    } else{
                    const group = ctx.openGroup(); // create an SVG group element
                    noteC.draw();
                    ctx.closeGroup();
                      setWrong(group)
                    }
                 }
                    notesX = (noteE.getAbsoluteX() * 4 + 10);
                    notesY = (noteE.getYs() * 4);
                  if ((clientY < (noteE.getYs() * 4 + 13))  && (clientY > (noteE.getYs() * 4 - 13))){
                       if (clientX > notesX){
                       tickContext.preFormat().setX(clientX/4 - notesX/4)
                       }

                     if (noteLetter == "E"){
                        noteE.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                        const group = ctx.openGroup(); // create an SVG group element
                        noteE.draw();
                        ctx.closeGroup();
                        setCorrect(group)
                     } else{
                         const group = ctx.openGroup(); // create an SVG group element
                         noteE.draw();
                         ctx.closeGroup();
                       setWrong(group)
                     }
                  }
                  }

                            notesX = (EnoteLines.getAbsoluteX() * 4 + 10);
                          notesY = (EnoteLines.getYs() * 4);
                        if ((clientY < (EnoteLines.getYs() * 4 + 13))  && (clientY > (EnoteLines.getYs() * 4 - 13))){
                             if (clientX > notesX){
                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                             }

                           if (noteLetter == "E"){
                              EnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                              const group = ctx.openGroup(); // create an SVG group element
                              EnoteLines.draw();
                              ctx.closeGroup();
                              setCorrect(group)
                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               EnoteLines.draw();
                               ctx.closeGroup();
                             setWrong(group)
                           }
                        }

                        notesX = (BnoteLines.getAbsoluteX() * 4 + 10);
                          notesY = (BnoteLines.getYs() * 4);
                        if ((clientY < (BnoteLines.getYs() * 4 + 13))  && (clientY > (BnoteLines.getYs() * 4 - 13))){
                             if (clientX > notesX){
                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                             }

                           if (noteLetter == "B"){
                              BnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                              const group = ctx.openGroup(); // create an SVG group element
                              BnoteLines.draw();
                              ctx.closeGroup();
                              setCorrect(group)
                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               BnoteLines.draw();
                               ctx.closeGroup();
                             setWrong(group)
                           }
                        }
                          notesX = (DnoteLines.getAbsoluteX() * 4 + 10);
                          notesY = (DnoteLines.getYs() * 4);
                        if ((clientY < (DnoteLines.getYs() * 4 + 13))  && (clientY > (DnoteLines.getYs() * 4 - 13))){
                             if (clientX > notesX){
                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                             }

                           if (noteLetter == "D"){
                              DnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                              const group = ctx.openGroup(); // create an SVG group element
                              DnoteLines.draw();
                              ctx.closeGroup();
                              setCorrect(group)
                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               DnoteLines.draw();
                               ctx.closeGroup();
                             setWrong(group)
                           }
                        }
                            notesX = (GnoteLines.getAbsoluteX() * 4 + 10);
                          notesY = (GnoteLines.getYs() * 4);
                        if ((clientY < (GnoteLines.getYs() * 4 + 13))  && (clientY > (GnoteLines.getYs() * 4 - 13))){
                             if (clientX > notesX){
                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                             }

                           if (noteLetter == "G"){
                              GnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                              const group = ctx.openGroup(); // create an SVG group element
                              GnoteLines.draw();
                              ctx.closeGroup();
                              setCorrect(group)
                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               GnoteLines.draw();
                               ctx.closeGroup();
                               setWrong(group)
                           }
                        }

                              notesX = (FnoteLines.getAbsoluteX() * 4 + 10);
                                                  notesY = (FnoteLines.getYs() * 4);
                                                if ((clientY < (FnoteLines.getYs() * 4 + 13))  && (clientY > (FnoteLines.getYs() * 4 - 13))){
                                                     if (clientX > notesX){
                                                     tickContext.preFormat().setX(clientX/4 - notesX/4)
                                                     }

                                                   if (noteLetter == "F"){
                                                      FnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                                      const group = ctx.openGroup(); // create an SVG group element
                                                      FnoteLines.draw();
                                                      ctx.closeGroup();
                                                      setCorrect(group)
                                                   } else{
                                                       const group = ctx.openGroup(); // create an SVG group element
                                                       FnoteLines.draw();
                                                       ctx.closeGroup();
                                                     setWrong(group)
                                                   }
                                                }


      }, false)



function setWrong(group) {
        android.javaScriptCorrectAnswer("false");
        group.classList.add('scroll');
     //   group.classList.add('fadeOut');
        const box = group.getBoundingClientRect();
        group.classList.add('scrolling');
}

function setCorrect(group){
        android.javaScriptCorrectAnswer(true);
}
