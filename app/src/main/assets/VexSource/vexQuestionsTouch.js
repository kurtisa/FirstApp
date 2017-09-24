VF = Vex.Flow;

// calculate correct note

var level
level = android.javaScriptGetTouchLevel();

if (level == '6' || level == '10'){
min = Math.ceil(0);
max = Math.floor(4);
} else if (level == '4' || level == '8'){
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
if (level == '6' ){
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
} else if (level == '4'){
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

}else if (level == '8'){
         switch (correctNoteNumber){
            case 0: noteLetter = "A";
            pitch = "/4";
                        break;
            case 1: noteLetter = "C";
            pitch = "/4";
                        break;
            case 2: noteLetter = "E";
            pitch = "/5";
                        break;
            case 3:	noteLetter = "G";
            pitch = "/5";
                        break;
            }

}else if (level == '10'){
         switch (correctNoteNumber){
            case 0: noteLetter = "G";
            pitch = "/4";
                        break;
            case 1: noteLetter = "B";
            pitch = "/4";
                        break;
            case 2: noteLetter = "D";
            pitch = "/5";
                        break;
            case 3:	noteLetter = "F";
            pitch = "/5";
                        break;
            case 4:	noteLetter = "A";
            pitch = "/5";
                        break;
            }
       }



  android.updateJavascriptNote(noteLetter);

   var notes;
  // var note;
var tickContext = new Vex.Flow.TickContext();
   var note =  new Vex.Flow.StaveNote({clef: stringClef, keys: [noteLetter + pitch], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);

    if (level == '4'){
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
    }else if (level == '10'){
     var anoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["A/3"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
     var fnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["F/3"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
     var dnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["D/3"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
     var bnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["B/2"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
     var gnoteLines =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["G/2"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
     tickContext.addTickable(anoteLines)
     tickContext.addTickable(fnoteLines)
     tickContext.addTickable(dnoteLines)
     tickContext.addTickable(bnoteLines)
     tickContext.addTickable(gnoteLines)
     }else if (level == '8'){
      var GnoteSpaces =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["G/3"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
      var EnoteSpaces =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["E/3"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
      var CnoteSpaces =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["C/3"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
      var AnoteSpaces =  new Vex.Flow.StaveNote({clef: stringClef, keys: ["A/2"], duration: "q",  auto_stem: true }).setContext(ctx).setStave(stave);
      tickContext.addTickable(GnoteSpaces)
      tickContext.addTickable(EnoteSpaces)
      tickContext.addTickable(CnoteSpaces)
      tickContext.addTickable(AnoteSpaces)
      }
    // Create a voice in 1/4


  /*  var voice = new Vex.Flow.Voice({
             num_beats: 1,
             beat_value: 4,
 // tickContext.preFormat().setX(0)
   /* var formatter = new Vex.Flow.Formatter().
    joinVoices([voice]).format([voice], 500);*/

      div.addEventListener('touchstart', function ref(e){
      var wrong = 0;
          var clientX;
          var clientY;
          var notesY;
          var notesX;
                var touchobj = e.changedTouches[0] // reference first touch point (ie: first finger)
                clientX = parseInt(touchobj.clientX) // get x position of touch point relative to left edge of browser
                clientY = parseInt(touchobj.clientY)
                e.preventDefault()

                if (level == '4'){
                notesX = (noteF.getAbsoluteX() * 4 + 10);
                notesY = (noteF.getYs() * 4);
                android.javaScriptGetTouch(clientX, clientY, notesY, notesX)

                 if ((clientY < (noteF.getYs() * 4 + 13))  && (clientY

              > (noteF.getYs() * 4 - 13))){
                    if (clientX > notesX){
                    tickContext.preFormat().setX(clientX/4 - notesX/4)
                    }
                  if (noteLetter == "F"){
                    noteF.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                    const group = ctx.openGroup(); // create an SVG group element
                    noteF.draw();
                    ctx.closeGroup();
                    setCorrect(group);
                     div.removeEventListener('touchstart', ref, false);
                  } else{
                  const group = ctx.openGroup(); // create an SVG group element
                  noteF.draw();
                  ctx.closeGroup();
                    setWrong(group);
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
                        setCorrect(group);
                        div.removeEventListener('touchstart', ref, false);


                    } else{
                    const group = ctx.openGroup(); // create an SVG group element
                    noteA.draw();
                    ctx.closeGroup();
                      setWrong(group);
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
                        setCorrect(group);
                        div.removeEventListener('touchstart', ref, false);

                    } else{
                    const group = ctx.openGroup(); // create an SVG group element
                    noteC.draw();
                    ctx.closeGroup();
                      setWrong(group);
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
                        setCorrect(group);
                        div.removeEventListener('touchstart', ref, false);

                     } else{
                         const group = ctx.openGroup(); // create an SVG group element
                         noteE.draw();
                         ctx.closeGroup();
                       setWrong(group);
                     }
                  }
                 } else if (level == '8'){
                                   notesX = (AnoteSpaces.getAbsoluteX() * 4 + 10);
                                   notesY = (AnoteSpaces.getYs() * 4);
                                   android.javaScriptGetTouch(clientX, clientY, notesY, notesX)

                                    if ((clientY < (AnoteSpaces.getYs() * 4 + 13))  && (clientY > (AnoteSpaces.getYs() * 4 - 13))){
                                       if (clientX > notesX){
                                       tickContext.preFormat().setX(clientX/4 - notesX/4)
                                       }
                                     if (noteLetter == "A"){
                                       AnoteSpaces.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                       const group = ctx.openGroup(); // create an SVG group element
                                       AnoteSpaces.draw();
                                       ctx.closeGroup();
                                       setCorrect(group);
                                        div.removeEventListener('touchstart', ref, false);
                                     } else{
                                     const group = ctx.openGroup(); // create an SVG group element
                                     AnoteSpaces.draw();
                                     ctx.closeGroup();
                                       setWrong(group);
                                     }
                                    }

                                       //Bass Clef C note space!
                                     notesX = (CnoteSpaces.getAbsoluteX() * 4 + 10);
                                     notesY = (CnoteSpaces.getYs() * 4);
                                     android.javaScriptGetTouch(clientX, clientY, notesY, notesX)
                                     // check if note is note A

                                    if ((clientY < (CnoteSpaces.getYs() * 4 + 13))  && (clientY > (CnoteSpaces.getYs() * 4 - 13))){
                                         if (clientX > notesX){
                                         tickContext.preFormat().setX(clientX/4 - notesX/4)
                                         }

                                       if (noteLetter == "C"){
                                           CnoteSpaces.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                           const group = ctx.openGroup(); // create an SVG group element
                                           CnoteSpaces.draw();
                                           ctx.closeGroup();
                                           setCorrect(group);
                                           div.removeEventListener('touchstart', ref, false);


                                       } else{
                                       const group = ctx.openGroup(); // create an SVG group element
                                       CnoteSpaces.draw();
                                       ctx.closeGroup();
                                         setWrong(group);
                                       }
                                    }

                                    notesX = (EnoteSpaces.getAbsoluteX() * 4 + 10);
                                    notesY = (EnoteSpaces.getYs() * 4);
                                    if ((clientY < (EnoteSpaces.getYs() * 4 + 13))  && (clientY > (EnoteSpaces.getYs() * 4 - 13))){
                                         if (clientX > notesX){
                                         tickContext.preFormat().setX(clientX/4 - notesX/4)
                                         }
                                       if (noteLetter == "E"){
                                           EnoteSpaces.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                           const group = ctx.openGroup(); // create an SVG group element
                                           EnoteSpaces.draw();
                                           ctx.closeGroup();
                                           setCorrect(group);
                                           div.removeEventListener('touchstart', ref, false);

                                       } else{
                                       const group = ctx.openGroup(); // create an SVG group element
                                       EnoteSpaces.draw();
                                       ctx.closeGroup();
                                         setWrong(group);
                                       }
                                    }
                                       notesX = (GnoteSpaces.getAbsoluteX() * 4 + 10);
                                       notesY = (GnoteSpaces.getYs() * 4);
                                     if ((clientY < (GnoteSpaces.getYs() * 4 + 13))  && (clientY > (GnoteSpaces.getYs() * 4 - 13))){
                                          if (clientX > notesX){
                                          tickContext.preFormat().setX(clientX/4 - notesX/4)
                                          }

                                        if (noteLetter == "G"){
                                           GnoteSpaces.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                           const group = ctx.openGroup(); // create an SVG group element
                                           GnoteSpaces.draw();
                                           ctx.closeGroup();
                                           setCorrect(group);
                                           div.removeEventListener('touchstart', ref, false);

                                        } else{
                                            const group = ctx.openGroup(); // create an SVG group element
                                            GnoteSpaces.draw();
                                            ctx.closeGroup();
                                          setWrong(group);
                                        }
                                     }
                                    }else if (level == '6'){

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
                              setCorrect(group);
                              div.removeEventListener('touchstart', ref, false);

                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               EnoteLines.draw();
                               ctx.closeGroup();
                             setWrong(group);
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
                              setCorrect(group);
                              div.removeEventListener('touchstart', ref, false);

                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               BnoteLines.draw();
                               ctx.closeGroup();
                             setWrong(group);
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
                              setCorrect(group);
                             div.removeEventListener('touchstart', ref, false);

                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               DnoteLines.draw();
                               ctx.closeGroup();
                             setWrong(group);
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
                              setCorrect(group);
                              div.removeEventListener('touchstart', ref, false);

                           } else{
                               const group = ctx.openGroup(); // create an SVG group element
                               GnoteLines.draw();
                               ctx.closeGroup();
                               setWrong(group);
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
                                                      setCorrect(group);
                                                      div.removeEventListener('touchstart', ref, false);

                                                   } else{
                                                       const group = ctx.openGroup(); // create an SVG group element
                                                       FnoteLines.draw();
                                                       ctx.closeGroup();
                                                     setWrong(group);
                                                   }
                                                }
                                                } else if (level == '10'){
                                                   notesX = (gnoteLines.getAbsoluteX() * 4 + 10);
                                                                          notesY = (gnoteLines.getYs() * 4);
                                                                        if ((clientY < (gnoteLines.getYs() * 4 + 13))  && (clientY > (gnoteLines.getYs() * 4 - 13))){
                                                                             if (clientX > notesX){
                                                                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                                                                             }

                                                                           if (noteLetter == "G"){
                                                                              gnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                                                              const group = ctx.openGroup(); // create an SVG group element
                                                                              gnoteLines.draw();
                                                                              ctx.closeGroup();
                                                                              setCorrect(group);
                                                                              div.removeEventListener('touchstart', ref, false);

                                                                           } else{
                                                                               const group = ctx.openGroup(); // create an SVG group element
                                                                               gnoteLines.draw();
                                                                               ctx.closeGroup();
                                                                             setWrong(group);
                                                                           }
                                                                        }

                                                                        notesX = (bnoteLines.getAbsoluteX() * 4 + 10);
                                                                          notesY = (bnoteLines.getYs() * 4);
                                                                        if ((clientY < (bnoteLines.getYs() * 4 + 13))  && (clientY > (bnoteLines.getYs() * 4 - 13))){
                                                                             if (clientX > notesX){
                                                                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                                                                             }

                                                                           if (noteLetter == "B"){
                                                                              bnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                                                              const group = ctx.openGroup(); // create an SVG group element
                                                                              bnoteLines.draw();
                                                                              ctx.closeGroup();
                                                                              setCorrect(group);
                                                                              div.removeEventListener('touchstart', ref, false);

                                                                           } else{
                                                                               const group = ctx.openGroup(); // create an SVG group element
                                                                               bnoteLines.draw();
                                                                               ctx.closeGroup();
                                                                             setWrong(group);
                                                                           }
                                                                        }
                                                                          notesX = (dnoteLines.getAbsoluteX() * 4 + 10);
                                                                          notesY = (dnoteLines.getYs() * 4);
                                                                        if ((clientY < (dnoteLines.getYs() * 4 + 13))  && (clientY > (dnoteLines.getYs() * 4 - 13))){
                                                                             if (clientX > notesX){
                                                                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                                                                             }

                                                                           if (noteLetter == "D"){
                                                                              dnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                                                              const group = ctx.openGroup(); // create an SVG group element
                                                                              dnoteLines.draw();
                                                                              ctx.closeGroup();
                                                                              setCorrect(group);
                                                                             div.removeEventListener('touchstart', ref, false);

                                                                           } else{
                                                                               const group = ctx.openGroup(); // create an SVG group element
                                                                               dnoteLines.draw();
                                                                               ctx.closeGroup();
                                                                             setWrong(group);
                                                                           }
                                                                        }
                                                                            notesX = (fnoteLines.getAbsoluteX() * 4 + 10);
                                                                          notesY = (fnoteLines.getYs() * 4);
                                                                        if ((clientY < (fnoteLines.getYs() * 4 + 13))  && (clientY > (fnoteLines.getYs() * 4 - 13))){
                                                                             if (clientX > notesX){
                                                                             tickContext.preFormat().setX(clientX/4 - notesX/4)
                                                                             }

                                                                           if (noteLetter == "F"){
                                                                              fnoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                                                              const group = ctx.openGroup(); // create an SVG group element
                                                                              fnoteLines.draw();
                                                                              ctx.closeGroup();
                                                                              setCorrect(group);
                                                                              div.removeEventListener('touchstart', ref, false);

                                                                           } else{
                                                                               const group = ctx.openGroup(); // create an SVG group element
                                                                               fnoteLines.draw();
                                                                               ctx.closeGroup();
                                                                               setWrong(group);
                                                                           }
                                                                        }

                                                                              notesX = (anoteLines.getAbsoluteX() * 4 + 10);
                                                                                                  notesY = (anoteLines.getYs() * 4);
                                                                                                if ((clientY < (anoteLines.getYs() * 4 + 13))  && (clientY > (anoteLines.getYs() * 4 - 13))){
                                                                                                     if (clientX > notesX){
                                                                                                     tickContext.preFormat().setX(clientX/4 - notesX/4)
                                                                                                     }

                                                                                                   if (noteLetter == "A"){
                                                                                                      anoteLines.setStyle({strokeStyle: "Lime", fillStyle: "Lime"});
                                                                                                      const group = ctx.openGroup(); // create an SVG group element
                                                                                                      anoteLines.draw();
                                                                                                      ctx.closeGroup();
                                                                                                      setCorrect(group);
                                                                                                      div.removeEventListener('touchstart', ref, false);

                                                                                                   } else{
                                                                                                       const group = ctx.openGroup(); // create an SVG group element
                                                                                                       anoteLines.draw();
                                                                                                       ctx.closeGroup();
                                                                                                     setWrong(group);
                                                                                                   }



                                                }
                                                }


      }, false)

function setWrong(group) {
        android.javaScriptCorrectAnswer('false');
        group.classList.add('scroll');
     //   group.classList.add('fadeOut');
        const box = group.getBoundingClientRect();
        group.classList.add('scrolling');
}
function setCorrect(group){
        android.javaScriptCorrectAnswer('true');
}