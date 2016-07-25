module.exports = {
	open: function (options, callback, scope) {
		cordova.exec(null, null, 'SocialNetworks', 'open', [options]);
	}
}
