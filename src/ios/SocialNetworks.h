
#import <Cordova/CDVPlugin.h>

@interface SocialNetworks : CDVPlugin

// Shows the email composer view with pre-filled data
- (void) open:(CDVInvokedUrlCommand*)command;
// Checks if the mail composer is able to send mails
- (void) isAvailable:(CDVInvokedUrlCommand*)command;

@end
