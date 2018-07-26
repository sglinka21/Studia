/* SHADER PROGRAM */
/* vertex shader source code */
var vertexShaderSrc= ""+
    "attribute vec4 aVertexPosition; \n"+
	"attribute vec2 aTexCoords; \n"+
	"varying vec2 TexCoords; \n"+
    "uniform vec3 uMove; \n"+
    "void main( void ) { \n"+
    "  gl_PointSize=16.0; \n"+
    "  gl_Position= aVertexPosition+ vec4( uMove, 0); \n"+
	"  TexCoords = aTexCoords; \n"+
    "} \n";

/* fragment shader source code */
var fragmentShaderSrc= ""+
    "precision mediump float; \n"+ 
	"varying vec2 TexCoords; \n"+
    "uniform sampler2D tex; \n"+ 
    "void main( void ) { \n"+
    "  gl_FragColor = texture2D( tex, TexCoords ); \n"+
    "} \n";



var gl; // GL context
var glObjects; // references to various GL objects
var html; // HTML objects
var data; // user data

var texCoordsFloat32Array=	new Float32Array([
    0,  0,
    0,  1,
    1,  1,
    1,  0,
]);

var dataInit= function(){
    data={};
    data.background=[0,0.5,0,.8];

    /* animated object */
    data.object1={};
    data.object1.speed=0.0005; // ?
    data.object1.direction= [0,0,0];
    // parameters for drawObject
    data.object1.position=[-0.9,-0.2,0];
    //data.object1.colorRGB=[.5, .5, .5];
    data.object1.bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, data.object1.bufferId );
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array([0.0,0.0,-.05,0.0,-.05,0.4,0.0,0.4]) , gl.STATIC_DRAW ); // load object's shape
	data.object1.texCoordsBuffer=gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, data.object1.texCoordsBuffer);
	gl.bufferData(gl.ARRAY_BUFFER, texCoordsFloat32Array, gl.STATIC_DRAW);
    data.object1.floatsPerVertex=2;   //-0.01,0.2,.01,.2,.01,-.2,-.01,-.2
    data.object1.NumberOfVertices=4;
    data.object1.drawMode=gl.TRIANGLE_FAN;
	//
	data.object2={};
    data.object2.speed=0.0005; // ?
    data.object2.direction= [0,0,0];
    // parameters for drawObject
    data.object2.position=[.9,-0.2,0];
    //data.object2.colorRGB=[.3, .2, .2];
    data.object2.bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, data.object2.bufferId );
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array([0.0,0.0,.05,0.0,.05,0.4,0.0,0.4]) , gl.STATIC_DRAW ); // load object's shape
	data.object2.texCoordsBuffer=gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, data.object2.texCoordsBuffer);
	gl.bufferData(gl.ARRAY_BUFFER, texCoordsFloat32Array, gl.STATIC_DRAW);
    data.object2.floatsPerVertex=2;
    data.object2.NumberOfVertices=4;
    data.object2.drawMode=gl.TRIANGLE_FAN;
	
    
	data.object3={};
    data.object3.speed=0.00055; // ?
    data.object3.direction= [1,0,0];
    // parameters for drawObject
    data.object3.position=[0.0,0,0];
    //data.object3.colorRGB=[0.0, 0.0, 0.0];
    data.object3.bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, data.object3.bufferId );
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array([-0.02,-0.02,0.02,-0.02,0.02,0.02,-0.02,0.02,]) , gl.STATIC_DRAW ); // load object's shape
	data.object3.texCoordsBuffer=gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, data.object3.texCoordsBuffer);
	gl.bufferData(gl.ARRAY_BUFFER, texCoordsFloat32Array, gl.STATIC_DRAW);
    data.object3.floatsPerVertex=2;
    data.object3.NumberOfVertices=4;
    data.object3.drawMode=gl.TRIANGLE_FAN;
	
	data.object4={};
    data.object4.direction= [1,0,0];
    // parameters for drawObject
    data.object4.position=[0.0,0,0.7];
//    data.object4.colorRGB=[1.0, 1.0, 1.0];
    data.object4.bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, data.object4.bufferId );
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array([-1,1,1,1,1,-1,-1,-1]) , gl.STATIC_DRAW ); // load object's shape
	data.object4.texCoordsBuffer=gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, data.object4.texCoordsBuffer);
	gl.bufferData(gl.ARRAY_BUFFER, texCoordsFloat32Array, gl.STATIC_DRAW);
    data.object4.floatsPerVertex=2;
    data.object4.NumberOfVertices=4;
    data.object4.drawMode=gl.TRIANGLE_FAN
	
	
    /* animation */
    data.animation={};
    data.animation.requestId=0;
//-0.9,0.2,-0.87,0.2,-0.87,-0.2,-0.9,-0.2
}

var drawObject=function( obj, texture ) {
    /* draw object obj */
    gl.useProgram( glObjects.shaderProgram );
    gl.lineWidth(3);
    gl.enableVertexAttribArray(glObjects.aVertexPositionLocation);
	gl.bindBuffer(gl.ARRAY_BUFFER, obj.bufferId ); /* refer to the buffer */
    gl.vertexAttribPointer(glObjects.aVertexPositionLocation, obj.floatsPerVertex, gl.FLOAT, false, 0 /* stride */, 0 /*offset */);
	
    gl.uniform3fv( glObjects.uMoveLocation, obj.position );
    
	gl.enableVertexAttribArray(glObjects.aTexCoordsLocation);
	gl.bindBuffer(gl.ARRAY_BUFFER, obj.texCoordsBuffer);
	gl.vertexAttribPointer(glObjects.aTexCoordsLocation, 2, gl.FLOAT, false, 0, 0);
	gl.activeTexture(gl.TEXTURE0+1 );
    gl.uniform1i(glObjects.tex1Location, 1 );
    gl.bindTexture(gl.TEXTURE_2D, texture);
	
	//gl.uniform3fv( glObjects.uColorRGBLocation, obj.colorRGB );
	
    gl.drawArrays(obj.drawMode, 0 /* offset */, obj.NumberOfVertices);
}

var redraw = function() {
    //var bg = data.background;
    /* prepare clean screen */
    gl.clearColor(0, 0, 0, 1);
	
	scorePlayer1Node.nodeValue = scorePlayer1.toFixed(0);  // no decimal place
    scorePlayer2Node.nodeValue = scorePlayer2.toFixed(0);
	
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    /* draw objects */
    drawObject(data.object1, glObjects.textureId1);
	drawObject(data.object2, glObjects.textureId2);
	drawObject(data.object4, glObjects.textureId3);
	drawObject(data.object3, glObjects.textureId4);
	
}

var animate=function(time) {
	
    //var timeDelta= time-data.animation.lastTime;
    //data.animation.lastTime= time ;
	
    //var x=  data.object1.position[0]+data.object1.direction[0]* data.object1.speed*timeDelta;
    var y1=  data.object1.position[1]+data.object1.direction[1]* data.object1.speed*time;
	
   // data.object1.position[0]= (x+3)%2 -1;
    data.object1.position[1]= (y1+3)%2 -1;
	
	//var x=  data.object4.position[0]+data.object4.direction[0]* data.object4.speed*timeDelta;
    var y2=  data.object2.position[1]+data.object2.direction[1]* data.object2.speed*time;

    //data.object4.position[0]= (x+3)%2 -1;
    data.object2.position[1]= (y2+3)%2 -1;

	var x3=  data.object3.position[0]+data.object3.direction[0]* data.object3.speed*time;
    var y3=  data.object3.position[1]+data.object3.direction[1]* data.object3.speed*time;
	
	
	data.object3.position[0]= (x3+3)%2 -1;
    data.object3.position[1]= (y3+3)%2 -1;
	
	
	
    redraw();
    gl.finish();
	
	
	
    
}
var scorePlayer1=0;
var scorePlayer2=0;
var game=function(time){
	var timeDelta= time-data.animation.lastTime;
    data.animation.lastTime= time ;
	
	
	if(data.object1.position[0] < data.object3.position[0] + 0.02 && data.object1.position[0] + 0.02 > data.object3.position[0] && data.object1.position[1]<data.object3.position[1] + 0.02 && data.object1.position[1]+.4>data.object3.position[1]){
			data.object3.direction[0] *= -1;
			data.object3.direction[1] -= -0.7*data.object1.direction[1];
			}
			
	else if(data.object2.position[0] < data.object3.position[0] + 0.02 && data.object2.position[0] + 0.02 > data.object3.position[0] && data.object2.position[1]<data.object3.position[1] + 0.02 && data.object2.position[1]+.4>data.object3.position[1]){
			data.object3.direction[0] *= -1;
			data.object3.direction[1] -= -0.7*data.object2.direction[1];
			}
	
	else if(data.object3.position[0]>.99){
		scorePlayer1++;
		data.object3.position[0]=0;
		data.object3.position[1]=0;
		data.object3.direction[0] *= -1;
		data.object3.direction[1]=0;
		}
	else if (data.object3.position[0]<-.99){
		scorePlayer2++;
		data.object3.position[0]=0;
		data.object3.position[1]=0;
		data.object3.direction[0] *= -1;
		data.object3.direction[1]=0;}
	else if(data.object3.position[1]>.99){
		data.object3.direction[1] *= -1;
	}
	else if(data.object3.position[1]<-.99){
		data.object3.direction[1] *= -1;
	}
	
	
	animate(timeDelta);
	
	data.animation.requestId = window.requestAnimationFrame(game);
	
	
}


var animationStart= function(){
    data.animation.lastTime = window.performance.now();
    data.animation.requestId = window.requestAnimationFrame(game);
}

var animationStop= function(){
    if (data.animation.requestId)
	window.cancelAnimationFrame(data.animation.requestId);
    data.animation.requestId = 0;
    redraw();
}

var createTexture2D= function(gl){
    /* parameters:
       gl -  WebGL contex
       textureUnit - texture unit to which the texture should be bound
    */
    var textureId=gl.createTexture();
    // gl.activeTexture(gl.TEXTURE0+textureUnit);
    gl.bindTexture(gl.TEXTURE_2D, textureId);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.CLAMP_TO_EDGE);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.CLAMP_TO_EDGE);
    return textureId;
}

var loadTexture2DFromImg= function(gl, img, textureId){
    /* use after  makeShaderProgram(gl) */
    /* Parameters:
       gl - WebGL context
       img - container of the image
       textureId - ID returned by  createMyTexture2D
       textureUnit - texture unit to which the texture should be bound
    */
    gl.pixelStorei(gl.UNPACK_FLIP_Y_WEBGL, true); 
    gl.bindTexture(gl.TEXTURE_2D, textureId);
    gl.texImage2D( gl.TEXTURE_2D, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, img);
};



var htmlInit= function() {
    html={};
    html.html=document.querySelector('#htmlId');
    html.canvas= document.querySelector('#canvasId');
	html.imgTex1=document.querySelector('#imgTex1');
	html.imgTex2=document.querySelector('#imgTex2');
	html.imgTex3=document.querySelector('#imgTex3');
	html.imgTex3=document.querySelector('#imgTex4');
};
var scorePlayer1Node = document.createTextNode("");
  var scorePlayer2Node = document.createTextNode("");

var glInit= function(canvas) {
    gl = canvas.getContext("experimental-webgl");
    gl.viewportWidth = canvas.width;
    gl.viewportHeight = canvas.height;
	
	var sPlayer1 = document.getElementById("player1");
	var sPlayer2 = document.getElementById("player2");


  // Add those text nodes where they need to go
  sPlayer1.appendChild(scorePlayer1Node);
  sPlayer2.appendChild(scorePlayer2Node);
	
	
	
    gl.enable(gl.DEPTH_TEST);
    gl.depthFunc(gl.LEQUAL);

    glObjects={}; 

    /* create executable shader program */
    glObjects.shaderProgram=compileAndLinkShaderProgram( gl, vertexShaderSrc, fragmentShaderSrc );
	
	glObjects.textureId1=createTexture2D(gl);
    glObjects.textureId2=createTexture2D(gl);
	glObjects.textureId3=createTexture2D(gl);
	glObjects.textureId4=createTexture2D(gl);

    loadTexture2DFromImg(gl, imgTex1, glObjects.textureId1);
    loadTexture2DFromImg(gl, imgTex2, glObjects.textureId2);
	loadTexture2DFromImg(gl, imgTex3, glObjects.textureId3);
	loadTexture2DFromImg(gl, imgTex4, glObjects.textureId4);
	
    /* attributes */
    glObjects.aVertexPositionLocation = gl.getAttribLocation(glObjects.shaderProgram, "aVertexPosition");
	glObjects.aTexCoordsLocation=gl.getAttribLocation(glObjects.shaderProgram, "aTexCoords");
	
    /* uniform variables */
    glObjects.uMoveLocation = gl.getUniformLocation(glObjects.shaderProgram, "uMove");
    glObjects.tex1Location = gl.getUniformLocation(glObjects.shaderProgram, "tex");

};

var compileAndLinkShaderProgram=function ( gl, vertexShaderSource, fragmentShaderSource ){
    var vertexShader = gl.createShader(gl.VERTEX_SHADER);
    gl.shaderSource(vertexShader, vertexShaderSource);
    gl.compileShader(vertexShader);
    if (!gl.getShaderParameter(vertexShader, gl.COMPILE_STATUS)) {
	console.log(gl.getShaderInfoLog(vertexShader));
	console.log(gl);
	return null;
    }

    var fragmentShader =gl.createShader(gl.FRAGMENT_SHADER);
    gl.shaderSource(fragmentShader, fragmentShaderSource);
    gl.compileShader(fragmentShader);
    if (!gl.getShaderParameter(fragmentShader, gl.COMPILE_STATUS)) {
	console.log(gl.getShaderInfoLog(fragmentShader));
	console.log(gl);
	return null;
    }

    var shaderProgram = gl.createProgram();
    gl.attachShader(shaderProgram, vertexShader);
    gl.attachShader(shaderProgram, fragmentShader);
    gl.linkProgram(shaderProgram);
    if (!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
	console.log("Could not initialise shaders");
	console.log(gl);
	return null;
    }
    // SUCCESS 
    return shaderProgram;
};


var callbackOnKeyDown =function (e){
    var code= e.which || e.keyCode;
    switch(code)
    {
    case 38: // up
		data.object2.direction=[0,1];
	if( data.animation.requestId == 0) animationStart();
	break;
    case 87: // W
        data.object1.direction=[0,1];
	if( data.animation.requestId == 0) animationStart();
	break;
    case 40: // down
		data.object2.direction=[0,-1];
	if( data.animation.requestId == 0) animationStart();
	break;	
    case 83: // S
        data.object1.direction=[0,-1];
	if( data.animation.requestId == 0) animationStart();
	break;
    case 32: // space
	if( data.animation.requestId == 0) {
	    animationStart();
	} else {
	    animationStop();
	} 
	break;
    }
}

window.onload= function(){
    htmlInit();
    glInit( html.canvas );
    dataInit();


    redraw(); 
    window.onkeydown=callbackOnKeyDown;
};
