import pygame
import random
import sys

pygame.init()
pygame.font.init()
all_fonts = pygame.font.get_fonts()
title_font = pygame.font.SysFont(all_fonts[1], 45)
main_font = pygame.font.SysFont(all_fonts[1], 30)
lost_font = pygame.font.SysFont(all_fonts[1], 30)

# Window
WIDTH, HEIGHT = 750, 750
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("assets/Space Battle")

try:
    # Load Images
    # Enemies Ships
    RED_SPACE_SHIP = pygame.image.load("assets/pixel_ship_red_small.png")
    GREEN_SPACE_SHIP = pygame.image.load("assets/pixel_ship_green_small.png")
    BLUE_SPACE_SHIP = pygame.image.load("assets/pixel_ship_blue_small.png")
    # Player Ship
    YELLOW_SPACE_SHIP = pygame.image.load("assets/pixel_ship_yellow.png")
    # Lasers
    RED_LASER = pygame.image.load("assets/pixel_laser_red.png")
    GREEN_LASER = pygame.image.load("assets/pixel_laser_green.png")
    BLUE_LASER = pygame.image.load("assets/pixel_laser_blue.png")
    YELLOW_LASER = pygame.image.load("assets/pixel_laser_yellow.png")
    # Background
    BG = pygame.transform.scale(pygame.image.load("assets/background-black.png"), (WIDTH, HEIGHT))
    # Window Icon
    pygame.display.set_icon(YELLOW_SPACE_SHIP)
except:
    print("\n** No se pudo cargar la informacion de la capa de datos **\n")

# Lasers Class
class Laser:
    def __init__(self, x, y, img):
        self.x = x
        self.y = y
        self.img = img
        self.mask = pygame.mask.from_surface(self.img)
        
    def draw(self,window):
        window.blit(self.img, (self.x, self.y))
        
    def move(self, vel):
        self.y += vel        
    
    def off_screen(self, height):
        return not(self.y <= height and self.y >= 0)
    
    def collision(self, obj):
        return collide(self, obj)
        
# General Ship Class
class Ship:
    COOLDOWN = 20
    
    def __init__(self, x, y, health=100):
        self.x = x
        self.y = y
        self.health = health
        self.ship_img = None
        self.laser_img = None
        self.lasers = []
        self.cool_down_counter = 0
    
    def draw(self, window):
        #pygame.draw.rect(window, (255,0,0), (self.x, self.y, 50, 50)) #draw a rectangle
        window.blit(self.ship_img, (self.x, self.y))
        for laser in self.lasers:
            laser.draw(window)
            
    def move_lasers(self, vel, obj):
        self.cooldown()
        for laser in self.lasers:
            laser.move(vel)
            if laser.off_screen(HEIGHT):
                self.lasers.remove(laser)              
            elif laser.collision(obj):
                obj.health -= 10
                self.lasers.remove(laser)
        
    def cooldown(self):
        if self.cool_down_counter >= self.COOLDOWN:
            self.cool_down_counter = 0
        elif self.cool_down_counter > 0:
            self.cool_down_counter += 1
        
    def shoot(self):
        if self.cool_down_counter == 0:
            laser = Laser(self.x, self.y, self.laser_img)
            self.lasers.append(laser)
            self.cool_down_counter = 1
            
    def get_width(self):
        return self.ship_img.get_width()
    def get_heigth(self):
        return self.ship_img.get_height()
            
# Player Ship Class 
class Player(Ship):
    def __init__(self, x, y, health=100):
        super().__init__(x, y, health)
        self.ship_img = YELLOW_SPACE_SHIP
        self.laser_img = YELLOW_LASER
        self.mask = pygame.mask.from_surface(self.ship_img)
        self.max_health = health
        
    def move_lasers(self, vel, objs):
        self.cooldown()
        for laser in self.lasers:
            laser.move(vel)
            if laser.off_screen(HEIGHT):
                self.lasers.remove(laser)              
            else:
                for obj in objs:
                    if laser.collision(obj):
                        objs.remove(obj)
                        if laser in self.lasers:
                            self.lasers.remove(laser)
                        
    def draw(self, window):
        super().draw(window)
        self.healthbar(window)
    
    def healthbar(self, window):
        pygame.draw.rect(window, (255,0,0), (self.x, self.y + self.ship_img.get_height() + 10, self.ship_img.get_width(), 10))
        pygame.draw.rect(window, (0,255,0), (self.x, self.y + self.ship_img.get_height() + 10, self.ship_img.get_width() * (self.health/self.max_health), 10))

# Enemies Ship Class     
class Enemy(Ship):
    COLOR_MAP = {
        "red": (RED_SPACE_SHIP, RED_LASER),
        "green": (GREEN_SPACE_SHIP, GREEN_LASER),
        "blue": (BLUE_SPACE_SHIP, BLUE_LASER)   
        }
    
    def __init__(self, x, y, color, health=100):
        super().__init__(x, y, health)
        self.ship_img, self.laser_img = self.COLOR_MAP[color]
        self.mask = pygame.mask.from_surface(self.ship_img)
        
    def move(self, vel):
        self.y += vel

# Collide Function
def collide(obj1, obj2):
    offset_x = obj2.x - obj1.x
    offset_y = obj2.y - obj1.y
    return obj1.mask.overlap(obj2.mask, (offset_x, offset_y)) != None
    
# Main Loop
def main():
    run = True
    FPS = 60
    level = 0
    lives = 5
        
    enemies = []
    wave_lenght = 5
    enemy_vel = 1
    laser_vel_enemy = 6
    
    player_vel = 7
    laser_vel = 10
    
    player = Player(325, 630)
    
    clock = pygame.time.Clock()
    
    lost = False
    lost_count = 0

    def redraw_window():
        # Draw Background
        WIN.blit(BG, (0,0))
        # Draw Text (level, lives)
        lives_label = main_font.render(f"Lives: {lives}", 1, (255,255,255))
        level_label = main_font.render(f"Level: {level}", 1, (255,255,255))
        WIN.blit(lives_label, (10, 10))
        WIN.blit(level_label, ((WIDTH - level_label.get_width() - 10), 10))
        # Draw Enemies
        for enemy in enemies:
            enemy.draw(WIN)
        # Draw Player Ship   
        player.draw(WIN)
        # Lost Message
        if lost:
            lost_label = lost_font.render("You Lost!!", 1, (255,255,255))
            WIN.blit(lost_label, (WIDTH/2 - lost_label.get_width()/2, 350))
        # Loop - Update Window
        pygame.display.update()

    while run:
        clock.tick(FPS)
        redraw_window()
        
        if lives <= 0 or player.health <= 0:
            lost = True
            lost_count += 1
            
        if lost:
            if lost_count > FPS * 3:
                run = False
            else:
                continue
        
        if len(enemies) == 0:
            level += 1
            wave_lenght += 5
            for i in range(wave_lenght):
                enemy = Enemy(random.randrange(80, WIDTH-80), random.randrange(-1500, -100), random.choice(["red", "blue", "green"]))
                enemies.append(enemy)
             
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                quit()

        keys = pygame.key.get_pressed()
        if keys[pygame.K_a] and player.x - player_vel > 0: #left
            player.x -= player_vel
        if keys[pygame.K_d] and player.x + player_vel + player.get_width() < WIDTH: #right
            player.x += player_vel
        if keys[pygame.K_w]and player.y - player_vel > 0: #up
            player.y -= player_vel
        if keys[pygame.K_s] and player.y + player_vel + player.get_heigth() + 15 < HEIGHT: #down
            player.y += player_vel
        if keys[pygame.K_SPACE]:
            player.shoot()
        
        for enemy in enemies[:]:
            enemy.move(enemy_vel)
            enemy.move_lasers(laser_vel_enemy, player)
            
            if random.randrange(0, 2*60) == 1:
                enemy.shoot()
                
            if collide(enemy, player):
                player.health -= 10
                enemies.remove(enemy)
            elif enemy.y + enemy.get_heigth() > HEIGHT:
                lives -= 1
                enemies.remove(enemy)
                                
        player.move_lasers(-laser_vel, enemies)

def main_menu():
    run = True
    while run:
        WIN.blit(BG, (0,0))
        title_label = title_font.render("Press the Mouse to begin...", 1, (255,255,255))
        WIN.blit(title_label, (WIDTH/2 - title_label.get_width()/2, 350))
        pygame.display.update()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
            if event.type == pygame.MOUSEBUTTONDOWN:
                main()
    pygame.quit()
                
main_menu()