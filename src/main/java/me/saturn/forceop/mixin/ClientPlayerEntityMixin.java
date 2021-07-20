package me.saturn.forceop.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

//This is the class that does all the handling of the mod
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Inject(method="sendChatMessage", at=@At("HEAD"), cancellable=true) //register when we type something in chat to run the below code
	public void onSendNewChatMessage(String msg, CallbackInfo ci){
		if(msg.toLowerCase().startsWith(".w") || msg.toLowerCase().startsWith(".write")){ //check if the message starts with .w or .write
			ci.cancel(); //cancel the message so we dont send it in chat
			String[] arr = msg.split(" "); //split it into an array so we can get the components

			if(arr.length < 4 || arr.length > 5){ //this is important so people dont put spaces and slip up
				notify("Incorrect usage, use .w <title> <text> <command> <(optional) author>, put a dash \"-\" anywhere where you would want a space");
				return;
			}
			if(!arr[3].startsWith("/")){
				notify("The 3rd argument is a command, meaning that it has to start with a /");
				return;
			}

			ItemStack book = new ItemStack(Items.WRITTEN_BOOK, 1); //make a new item that is a written book
			String author = arr.length == 5 ? arr[4] : MinecraftClient.getInstance().getSession().getUsername(); //get the players username and store it so we can use it for the author of the book
			String title = arr[1].replace("-", " "); //set the title of the book
			String text = arr[2].replace("-", " ").replace("\\\\", "\\"); //set the text inside the book
			String command = arr[3].replace("-", " ").replace("\\\\", "\\"); //set the command variable to the third peram

			try{ //try loop to catch any other errors (next line is a bit long so you may have to scroll) ----->
				NbtCompound tag = StringNbtReader.parse(String.format("{Creator:'Armada',title:'%s',author:'%s',pages:[\"{'text':'%s                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ','clickEvent':{'action':'run_command','value':'%s'}}\",\"{'text':''}\",\"{'text'}\"]}", title, author, text, command)); //set the nbt of the itemstack, including the variables we set earlier
				book.setTag(tag); //update the book so it has the nbt we generated above
			}catch(CommandSyntaxException e){
				notify("Something went wrong while parsing nbt...");
				notify("Usage, use .w <title> <text> <command> <(optional) author>, put a dash \"-\" anywhere where you would want a space");
				return;
			}

			notify("Created book!");
			MinecraftClient.getInstance().getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(MinecraftClient.getInstance().player.getInventory().selectedSlot + 36, book)); //Put the book in the players hand
		}
	}

	private void notify(String text){
		MinecraftClient.getInstance().player.sendMessage(Text.of(Formatting.RED + text), false); //little notify method to send chat to the player when they do something wrong
	}
	
	private void notifySuccess(String text){
		MinecraftClient.getInstance().player.sendMessage(Text.of(Formatting.GREEN + text), false); //little notify method to send chat to the player when they do something wrong
	}
}
