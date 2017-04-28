const path = require('path');
const express = require('express');
const app = express();
const multer = require('multer');
const fs = require('fs');
const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
const tokenLength = 4;
let testName = 'test001';
let uploadedImagesRoute = [];

app.use(express.static(path.join(__dirname, 'public')));

/**
 * Generates a random token
 * @param  {integer} pLength - token length
 * @return {string} generatedToken - a new random token
 */
function generateRandomToken(pLength){
    let generatedToken = '';
    let selectedChar = '';
    for(let i=0;i<pLength;i++){
        selectedChar = Math.floor(Math.random() * (chars.length));
        generatedToken += chars.charAt(selectedChar);
    }
    return generatedToken;
};

testName = generateRandomToken(tokenLength);

const storage = multer.diskStorage({
	destination: function(req, file, cb){
		const dir = '../uploaded_images/'+testName;
		if (!fs.existsSync(dir)){
			fs.mkdirSync(dir);
		}
		cb(null,dir)
	},
	filename: function(req, file, cb){
		cb(null, file.originalname);
	}
});

const upload = multer({storage:storage});


app.get('/uploaded_images/*', function (req, res) {
    res.sendFile(path.join(__dirname +'/..'+decodeURI(req.url)));
});


app.post('/sendfiles',upload.any(),function(req,res,next){
	//console.log(req);
	//res.send('finalizado');
	//testName = req.body.myName;
	//console.log(req.files);
	
	uploadedImagesRoute = req.files.map(function(image){
		return '"'+image.path+'"';
	});
	console.log(uploadedImagesRoute);
	const lastUsedToken = testName;
	testName = generateRandomToken(tokenLength); 
	//res.send(lastUsedToken);
	/*res.redirect('http://192.168.43.218:3001/results?token='+lastUsedToken
    +'&images='+encodeURI( uploadedImagesRoute ));*/
    res.redirect('http://192.168.43.129:8080/Prepro/AppServer?token='+lastUsedToken
    +'&images='+encodeURI( uploadedImagesRoute ));
},upload.any());

app.get('/jose',function(req,res){
	res.send('hey');
});

app.listen(3001,'0.0.0.0');
console.log('Server Ready');