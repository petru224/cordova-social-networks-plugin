
#import "SocialNetworks.h"
#import "Cordova/NSData+Base64.h"
#import "Cordova/CDVAvailability.h"
#import <MobileCoreServices/MobileCoreServices.h>

#include "TargetConditionals.h"


@implementation SocialNetworks

- (void) open:(CDVInvokedUrlCommand*)command
{
     UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"ROFL" 
                                                    message:@"Dee dee doo doo." 
                                                    delegate:self 
                                                    cancelButtonTitle:@"OK" 
                                                    otherButtonTitles:nil];
    [alert show];  
}
@end
