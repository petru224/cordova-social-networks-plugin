
#import "SocialNetworks.h"


@implementation SocialNetworks

- (void) open:(CDVInvokedUrlCommand*)command
{
    NSDictionary* args = [command.arguments objectAtIndex:0];
    NSString* app = [args valueForKey:@"app"];
    NSString* uri = [args valueForKey:@"uri"];
    NSString* url = [args valueForKey:@"url"];
    NSLog(@"%@",args);
     NSURL *facebookURL = [NSURL URLWithString:uri];
        if (![[UIApplication sharedApplication] openURL:facebookURL])
        {
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:url]];
        }
   
}
@end
